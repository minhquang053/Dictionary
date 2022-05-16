Set oShell = CreateObject ("Wscript.Shell") 
Dim strArgs
strArgs = "cmd /c subrun.bat"
oShell.Run strArgs, 0, false