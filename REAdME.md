Test
=====
In PowerShell: invoke-restmethod "http://localhost/cmd?attr=Get-Time" -Method Post

POST http://<server_ip>/cmd?attr = command

* http://localhost/cmd?attr=Get-LocalUser

* http://localhost/cmd?attr=Get-Service

* http://localhost/cmd?attr=Get-Time

* http://localhost/cmd?attr=Test-Connection 127.0.0.1

* http://localhost/cmd?attr=Test-NetConnection

* http://localhost/cmd?attr=Hello-World

* http://localhost/cmd?attr=ping 127.0.0.1

* http://localhost/cmd?attr=Resolve-DNSName sberbank.ru
     
HOST
====
Execute following command in PowerShell

 Set-ExecutionPolicy -ExecutionPolicy BYPASS
 
 COMMANDS
 ========
 Diagnostic
 ----------
 
* Test-Connection
 
* Test-NetConnection
 
* Resolve-DNSName
 
 Monitoring
 ----------
 
* Get-Eventlog
 
* Get-Process
 
* Get-Service
 
 FireWall Configuration
 ----------------------
 
* Get-NetFirewallRule
 
* Set-NetFirewallRule
 
* Enable-NetFirewallRule
 
* Disable-NetFirewallRule

Get Data from local invoke REST
-------------------------------
http://localhost/cmd?attr=invoke-restmethod%20%22http://localhost/cmd?attr=Get-Time%22%20-Method%20Get

Culture
-------
Get-Culture

Run a command on a remote server
--------------------------------
Invoke-Command -ComputerName server01 -Credential domain01\user01 -ScriptBlock {Get-Culture}


Run a script on all the computers listed in a text file
-------------------------------------------------------
Invoke-Command -ComputerName (Get-Content Servers.txt) -FilePath C:\Scripts\Sample.ps1 -ArgumentList Process, Service