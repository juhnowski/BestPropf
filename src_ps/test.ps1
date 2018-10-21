IF (-Not (Get-ADOrganizationalUnit -Filter 'Name -like "Sales"' |FT Name))
    {
    New-ADOrganizationalUnit -Name Sales -Path "DC=juhnowski,DC=com"
    }