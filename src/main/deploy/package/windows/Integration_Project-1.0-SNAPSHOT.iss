[Setup]
AppId={{UPDATE-DETECTION}}
AppName=Integration_Project-1.0-SNAPSHOT
AppVersion=1.0
AppVerName=IntegrationProject-1.0-SNAPSHOT 1.0
AppPublisher=gustavo
AppComments=IntegrationProject-1.0
AppCopyright=Copyright (C) 2023
;AppPublisherURL=http://java.com/
;AppSupportURL=http://java.com/
;AppUpdatesURL=http://java.com/
DefaultDirName={localappdata}\Integration_Project-1.0-SNAPSHOT
DisableStartupPrompt=Yes
DisableDirPage=Yes
DisableProgramGroupPage=Yes
DisableReadyPage=Yes
DisableFinishedPage=Yes
DisableWelcomePage=Yes
DefaultGroupName=Einstein
;Optional License
LicenseFile=
;WinXP or above
MinVersion=6
OutputBaseFilename=Integration_Project-1.0-SNAPSHOT
Compression=lzma
SolidCompression=yes
PrivilegesRequired=lowest
SetupIconFile=Integration_Project-1.0-SNAPSHOT\Integration_Project-1.0-SNAPSHOT.ico
UninstallDisplayIcon={app}\IntegrationProject-1.0.ico
UninstallDisplayName=Integration_Project-1.0-SNAPSHOT
WizardImageStretch=No
WizardSmallImageFile=Integration_Project-1.0-SNAPSHOT-setup-icon.bmp
ArchitecturesInstallIn64BitMode=



[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"
Name: "brazilianportuguese"; MessagesFile: "compiler:Languages\BrazilianPortuguese.isl"

[Files]
Source: "Integration_Project-1.0-SNAPSHOT\Integration_Project-1.0-SNAPSHOT.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "Integration_Project-1.0-SNAPSHOT\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\Integration_Project-1.0-SNAPSHOT";Filename: "{app}\Integration_Project-1.0-SNAPSHOT.exe"; WorkingDir: "{app}"; IconFilename: "Integration_Project-1.0-SNAPSHOT.ico"
Name: "{commondesktop}\Integration_Project-1.0-SNAPSHOT";Filename: "{app}\Integration_Project-1.0-SNAPSHOT.exe"; WorkingDir: "{app}"; IconFilename:"Integration_Project-1.0-SNAPSHOT.ico"

[Run]
Filename: "{app}\Integration_Project-1.0-SNAPSHOT.exe"; Description:"{cm:LaunchProgram,Integration_Project-1.0-SNAPSHOT}"; Flags: nowait postinstall skipifsilent;
Filename: "{app}\Integration_Project-1.0-SNAPSHOT.exe"; Parameters:"-install  -svcName ""Integration_Project-1.0-SNAPSHOT"" -svcDesc ""Integration_Project-1.0-SNAPSHOT"" -mainExe ""Integration_Project-1.0-SNAPSHOT.exe"" ";

[UninstallRun]
Filename: "{app}\Integration_Project-1.0-SNAPSHOT.exe "; Parameters: "-uninstall -svcName IntegrationProject-1.0 -stopOnUninstall";