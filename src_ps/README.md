Test-Path $Profile
New-Item -Path $Profile -Type File -Force
Notepad $Profile

Function Get-Time  {
	Get-Date -Format hh:mm
}


Get-ExecutionPolicy    
Get-ExecutionPolicy -List
 Get-ExecutionPolicy -Scope CurrentUser
Set-ExecutionPolicy -ExecutionPolicy BYPASS

Function Hello-World 
{
	Write-Host "Well, hello there, world!"
}

Import-Module //DESKTOP-RCQK0DN/src_ps/Hello

���, ��������� � �������
$env:PSModulePath = $env:PSModulePath + ";//DESKTOP-RCQK0DN/src_ps/Modules"
� ����� �����������:
Import-Module Hello
��������� Hello-World
------------------------------------------------------
