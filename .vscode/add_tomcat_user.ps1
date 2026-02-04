$tomcatHome = 'C:\Users\HP\OneDrive\Apps\Desktop\Sem V\webtech\apache-tomcat-8.5.93-windows-x64\apache-tomcat-8.5.93'
$env:CATALINA_HOME = $tomcatHome
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-17.0.12_windows-x64_bin\jdk-17.0.12'
$tomcatConf = Join-Path $tomcatHome 'conf\tomcat-users.xml'
$backup = "$tomcatConf.bak_$(Get-Date -Format yyyyMMddHHmmss)"
Copy-Item -Path $tomcatConf -Destination $backup -Force

[xml]$xml = Get-Content -Path $tomcatConf -Raw

# Generate a random 16-char password
$pw = -join ((33..126) | Get-Random -Count 16 | ForEach-Object {[char]$_})
$user = 'admin'

# Ensure role elements exist
$neededRoles = @('manager-gui','admin-gui')
$rolesNode = $xml.'tomcat-users'
foreach ($r in $neededRoles) {
    if (-not ($rolesNode.role | Where-Object { $_.rolename -eq $r })) {
        $roleElem = $xml.CreateElement('role')
        $attr = $xml.CreateAttribute('rolename')
        $attr.Value = $r
        $roleElem.Attributes.Append($attr) | Out-Null
        $rolesNode.AppendChild($roleElem) | Out-Null
    }
}

# Add user if missing
$existing = $rolesNode.user | Where-Object { $_.username -eq $user }
if (-not $existing) {
    $userElem = $xml.CreateElement('user')
    $attrU = $xml.CreateAttribute('username'); $attrU.Value = $user; $userElem.Attributes.Append($attrU) | Out-Null
    $attrP = $xml.CreateAttribute('password'); $attrP.Value = $pw; $userElem.Attributes.Append($attrP) | Out-Null
    $attrR = $xml.CreateAttribute('roles'); $attrR.Value = ($neededRoles -join ','); $userElem.Attributes.Append($attrR) | Out-Null
    $rolesNode.AppendChild($userElem) | Out-Null
    $xml.Save($tomcatConf)
}

# Restart Tomcat
& "$tomcatHome\bin\shutdown.bat" 2>$null
Start-Sleep -Seconds 2
& "$tomcatHome\bin\startup.bat"
Start-Sleep -Seconds 2

# Print credentials
Write-Output "CREDS:$user|$pw"

# Show recent catalina log tail
Get-ChildItem -Path (Join-Path $tomcatHome 'logs') -Filter 'catalina*.log' -ErrorAction SilentlyContinue | Sort-Object LastWriteTime -Descending | Select-Object -First 1 | ForEach-Object { Get-Content -Path $_.FullName -Tail 200 }
