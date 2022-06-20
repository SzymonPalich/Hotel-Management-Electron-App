import { app, protocol, BrowserWindow } from 'electron'
import { createProtocol } from 'vue-cli-plugin-electron-builder/lib'
import installExtension, { VUEJS3_DEVTOOLS } from 'electron-devtools-installer'
import options from '../spurvago.config.json'
import windowSizes from '../windowconfig.json'
import * as child_process from 'child_process'

// Window size
// small - 1350x700
// large - 1800x1000

const winSize = options.size == "small" ? windowSizes.small : windowSizes.large;

// Scheme must be registered before the app is ready
protocol.registerSchemesAsPrivileged([
  { scheme: 'app', privileges: { secure: true, standard: true } }
])

async function createWindow() {
  // Create the browser window.
  const win = new BrowserWindow({
    width: winSize.width,
    height: winSize.height,
    webPreferences: {
      // Use pluginOptions.nodeIntegration, leave this alone
      // See nklayman.github.io/vue-cli-plugin-electron-builder/guide/security.html#node-integration for more info
      nodeIntegration: (process.env
        .ELECTRON_NODE_INTEGRATION as unknown) as boolean,
      contextIsolation: !process.env.ELECTRON_NODE_INTEGRATION
    },
    resizable: false
  })


  const jarPath = app.getPath("exe").replace('\\hotel.exe', '') + "\\resources" + '\\Server.jar';
  const child = child_process.spawn(
    'java', ['-jar', jarPath]
    );

  win.removeMenu()

  if (process.env.WEBPACK_DEV_SERVER_URL) {
    // Load the url of the dev server if in development mode
    await win.loadURL(process.env.WEBPACK_DEV_SERVER_URL as string)
    if (!process.env.IS_TEST) win.webContents.openDevTools()
  } else {
    createProtocol('app')
    // Load the index.html when not in development
    setTimeout(function () {
      win.loadURL('app://./index.html')
    }, 10000)

    win.webContents.openDevTools()
  }

  win.on('closed', function () {
    child_process.exec('taskkill /f /im java.exe');
    child.kill();
  })
}


// Quit when all windows are closed.
app.on('window-all-closed', () => {
  // On macOS it is common for applications and their menu bar
  // to stay active until the user quits explicitly with Cmd + Q
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', () => {
  // On macOS it's common to re-create a window in the app when the
  // dock icon is clicked and there are no other windows open.
  if (BrowserWindow.getAllWindows().length === 0) createWindow()
})

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.on('ready', async () => {
  // Install Vue Devtools
  try {
    await installExtension(VUEJS3_DEVTOOLS)
  }
  catch (e: unknown) {
    if (typeof e === "string") {
      console.error('Vue Devtools failed to install:', e.toString())
    }
    else {
      console.error("Unknown error")
    }
  }
  createWindow()
})
