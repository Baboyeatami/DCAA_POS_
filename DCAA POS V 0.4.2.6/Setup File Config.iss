; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "DCAA Point of Sales "
#define MyAppVersion "1.1"
#define MyAppExeName "DCAA_POS.exe"

[Setup]
; NOTE: The value of AppId uniquely identifies this application. Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{608F1372-A008-41C8-AE70-CDF890F6A2AB}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
DefaultDirName=C:\{#MyAppName}
DisableDirPage=yes
DisableProgramGroupPage=yes
; Uncomment the following line to run in non administrative install mode (install for current user only.)
;PrivilegesRequired=lowest
OutputDir=C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\Installers
OutputBaseFilename=DCAA Point of Sales  1.1 
SetupIconFile=C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\DCAA POS V 0.4.2.6\DCAA_Logo.ico
Compression=lzma
SolidCompression=yes
WizardStyle=modern

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\DCAA POS V 0.4.2.6\DCAA_POS.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\DCAA POS V 0.4.2.6\DCAA Logo small.jpg"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\DCAA POS V 0.4.2.6\DCAA_Logo.ico"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\DCAA POS V 0.4.2.6\DCAA_POS.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\DCAA POS V 0.4.2.6\DCAA_POS_.html"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\DCAA POS V 0.4.2.6\DCAA_POS_.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\DCAA POS V 0.4.2.6\DCAA_POS_.jnlp"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\DCAA POS V 0.4.2.6\image.png"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\DCAA POS V 0.4.2.6\no-avatar.png"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\DCAA POS V 0.4.2.6\opencv_java3416.dll"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\DCAA POS V 0.4.2.6\Wrapper settings.xml"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\DCAA POS V 0.4.2.6\lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "C:\Users\Jamie\Documents\NetBeansProjects\DCAA_POS_\DCAA POS V 0.4.2.6\reports\*"; DestDir: "{app}\reports"; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{autoprograms}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{autodesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: nowait postinstall skipifsilent
