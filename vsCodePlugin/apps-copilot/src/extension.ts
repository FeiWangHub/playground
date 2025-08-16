import * as vscode from 'vscode';

// Tree view item class
class AppItem extends vscode.TreeItem {
    constructor(
        public readonly label: string,
        public readonly collapsibleState: vscode.TreeItemCollapsibleState,
        public readonly description?: string
    ) {
        super(label, collapsibleState);
        this.tooltip = `${this.label} - ${this.description}`;
        this.iconPath = new vscode.ThemeIcon('symbol-class');
    }
}

// Tree view data provider
class AppsProvider implements vscode.TreeDataProvider<AppItem> {
    private _onDidChangeTreeData: vscode.EventEmitter<AppItem | undefined | null | void> = new vscode.EventEmitter<AppItem | undefined | null | void>();
    readonly onDidChangeTreeData: vscode.Event<AppItem | undefined | null | void> = this._onDidChangeTreeData.event;

    constructor() {}

    refresh(): void {
        this._onDidChangeTreeData.fire();
    }

    getTreeItem(element: AppItem): vscode.TreeItem {
        return element;
    }

    getChildren(element?: AppItem): Thenable<AppItem[]> {
        if (!element) {
            // Root level applications
            return Promise.resolve([
                new AppItem('Slack', vscode.TreeItemCollapsibleState.None, 'Communication'),
                new AppItem('GitHub', vscode.TreeItemCollapsibleState.None, 'Development'),
                new AppItem('Notion', vscode.TreeItemCollapsibleState.None, 'Productivity'),
                new AppItem('Figma', vscode.TreeItemCollapsibleState.None, 'Design')
            ]);
        }
        return Promise.resolve([]);
    }
}

class AppsCopilotWebviewProvider implements vscode.WebviewViewProvider {
    public static readonly viewType = 'apps-copilot.chatView';

    constructor(private readonly _extensionUri: vscode.Uri) {}

    public resolveWebviewView(
        webviewView: vscode.WebviewView,
        context: vscode.WebviewViewResolveContext,
        _token: vscode.CancellationToken,
    ) {
        webviewView.webview.options = {
            enableScripts: true,
            localResourceRoots: [this._extensionUri]
        };

        webviewView.webview.html = this._getHtmlForWebview(webviewView.webview);

        // Handle messages from webview
        webviewView.webview.onDidReceiveMessage(
            message => {
                switch (message.type) {
                    case 'sendMessage':
                        vscode.window.showInformationMessage(`Received message: ${message.text}`);
                        // AI processing logic can be added here
                        webviewView.webview.postMessage({
                            type: 'response',
                            text: `AI Reply: I received your message "${message.text}"`
                        });
                        break;
                    case 'undo':
                        vscode.window.showInformationMessage('Undo action triggered');
                        // Implement undo logic
                        break;
                    case 'redo':
                        vscode.window.showInformationMessage('Redo action triggered');
                        // Implement redo logic
                        break;
                    case 'newChat':
                        vscode.window.showInformationMessage('New chat started');
                        // Clear chat history logic
                        break;
                    case 'showHistory':
                        vscode.window.showInformationMessage('Showing chat history');
                        // Show history logic
                        break;
                    case 'showMCPServers':
                        vscode.window.showInformationMessage('Showing MCP Servers');
                        // Show MCP servers configuration
                        break;
                    case 'showAccount':
                        vscode.window.showInformationMessage('Showing account settings');
                        // Show account management
                        break;
                    case 'showSettings':
                        vscode.window.showInformationMessage('Showing settings');
                        // Show extension settings
                        break;
                    case 'close':
                        vscode.commands.executeCommand('workbench.action.closeSidebar');
                        break;
                }
            },
            undefined,
            []
        );
    }

    private _getHtmlForWebview(webview: vscode.Webview) {
        const logoUri = webview.asWebviewUri(vscode.Uri.joinPath(this._extensionUri, 'logo.svg'));
        
        return `<!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>APPs Copilot</title>
            <style>
                body {
                    font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
                    margin: 0;
                    padding: 20px;
                    background-color: var(--vscode-editor-background);
                    color: var(--vscode-editor-foreground);
                    display: flex;
                    flex-direction: column;
                    height: 100vh;
                    box-sizing: border-box;
                }
                
                .container {
                    display: flex;
                    flex-direction: column;
                    height: 100%;
                }
                
                .logo-section {
                    text-align: center;
                    margin-bottom: 30px;
                    padding: 20px 0;
                }
                
                .logo {
                    width: 64px;
                    height: 64px;
                    margin: 0 auto 20px;
                    display: block;
                }
                
                .main-title {
                    font-size: 24px;
                    font-weight: 600;
                    margin: 0 0 8px 0;
                    color: var(--vscode-titleBar-activeForeground);
                    text-align: center;
                }
                
                .subtitle {
                    font-size: 14px;
                    color: var(--vscode-descriptionForeground);
                    margin: 0;
                    line-height: 1.4;
                    text-align: center;
                }
                
                .chat-container {
                    flex: 1;
                    display: flex;
                    flex-direction: column;
                    min-height: 0;
                }
                
                .messages {
                    flex: 1;
                    overflow-y: auto;
                    margin-bottom: 20px;
                    padding: 10px;
                    background-color: var(--vscode-input-background);
                    border: 1px solid var(--vscode-input-border);
                    border-radius: 6px;
                    min-height: 200px;
                }
                
                .message {
                    margin-bottom: 12px;
                    padding: 8px 12px;
                    border-radius: 6px;
                    max-width: 80%;
                }
                
                .message.user {
                    background-color: var(--vscode-button-background);
                    color: var(--vscode-button-foreground);
                    margin-left: auto;
                    text-align: right;
                }
                
                .message.assistant {
                    background-color: var(--vscode-badge-background);
                    color: var(--vscode-badge-foreground);
                }
                
                .input-container {
                    position: relative;
                    margin-bottom: 0;
                }
                
                .input-box {
                    width: 100%;
                    min-height: 36px;
                    max-height: 120px;
                    padding: 8px 40px 8px 12px;
                    border: 1px solid var(--vscode-input-border);
                    border-radius: 6px;
                    background-color: var(--vscode-input-background);
                    color: var(--vscode-input-foreground);
                    font-family: inherit;
                    font-size: 14px;
                    resize: none;
                    outline: none;
                    box-sizing: border-box;
                }
                
                .input-box:focus {
                    border-color: var(--vscode-focusBorder);
                }
                
                .send-button {
                    position: absolute;
                    right: 6px;
                    bottom: 6px;
                    width: 28px;
                    height: 28px;
                    background-color: var(--vscode-button-background);
                    color: var(--vscode-button-foreground);
                    border: none;
                    border-radius: 4px;
                    cursor: pointer;
                    font-size: 16px;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    transition: background-color 0.2s;
                    padding: 0;
                }
                
                .send-button:hover {
                    background-color: var(--vscode-button-hoverBackground);
                }
                
                .send-button:disabled {
                    opacity: 0.5;
                    cursor: not-allowed;
                }
            </style>
        </head>
        <body>
            <div class="container">
            <div class="logo-section">
            <img src="${logoUri}" alt="Apps Copilot Logo" class="logo">
            <h1 class="main-title">APPs Copilot - Plugin</h1>
            <p class="subtitle">AI Agent connecting over 20,000+ APIs in one place!</p>
            </div>
            
            <div class="chat-container">
                <div class="messages" id="messages">
                    <div class="message assistant">
                        👋 Welcome to APPs Copilot! I can help you connect and use various APIs. What can I help you with?
                    </div>
                </div>
                
                <div class="input-container">
                    <textarea 
                        id="messageInput" 
                        class="input-box" 
                        placeholder="Enter your message..."
                        rows="1"
                    ></textarea>
                    <button id="sendButton" class="send-button">➤</button>
                </div>
            </div>
            
            <script>
                const vscode = acquireVsCodeApi();
                const messageInput = document.getElementById('messageInput');
                const sendButton = document.getElementById('sendButton');
                const messagesContainer = document.getElementById('messages');
                
                // Auto-adjust input box height
                messageInput.addEventListener('input', function() {
                    this.style.height = 'auto';
                    this.style.height = Math.min(this.scrollHeight, 120) + 'px';
                });
                
                // Send message
                function sendMessage() {
                    const text = messageInput.value.trim();
                    if (text) {
                        // Add user message to interface
                        addMessage(text, 'user');
                        
                        // Send to extension
                        vscode.postMessage({
                            type: 'sendMessage',
                            text: text
                        });
                        
                        messageInput.value = '';
                        messageInput.style.height = 'auto';
                    }
                }
                
                // Add message to interface
                function addMessage(text, sender) {
                    const messageDiv = document.createElement('div');
                    messageDiv.className = \`message \${sender}\`;
                    messageDiv.textContent = text;
                    messagesContainer.appendChild(messageDiv);
                    messagesContainer.scrollTop = messagesContainer.scrollHeight;
                }
                
                // Listen for messages from extension
                window.addEventListener('message', event => {
                    const message = event.data;
                    if (message.type === 'response') {
                        addMessage(message.text, 'assistant');
                    }
                });
                
                // Event listeners
                sendButton.addEventListener('click', sendMessage);
                
                messageInput.addEventListener('keydown', function(e) {
                    if (e.key === 'Enter' && !e.shiftKey) {
                        e.preventDefault();
                        sendMessage();
                    }
                });
            </script>
        </body>
        </html>`;
    }
}

export function activate(context: vscode.ExtensionContext) {
    console.log('Congratulations, your extension "apps-copilot" is now active!');

    // Register webview provider
    const webviewProvider = new AppsCopilotWebviewProvider(context.extensionUri);
    context.subscriptions.push(
        vscode.window.registerWebviewViewProvider(
            AppsCopilotWebviewProvider.viewType,
            webviewProvider
        )
    );

    // Register toolbar commands
    const undoCommand = vscode.commands.registerCommand('apps-copilot.undo', () => {
        vscode.window.showInformationMessage('Undo clicked');
    });

    const redoCommand = vscode.commands.registerCommand('apps-copilot.redo', () => {
        vscode.window.showInformationMessage('Redo clicked');
    });

    const newChatCommand = vscode.commands.registerCommand('apps-copilot.newChat', () => {
        vscode.window.showInformationMessage('New Chat clicked');
    });

    const historyCommand = vscode.commands.registerCommand('apps-copilot.history', () => {
        vscode.window.showInformationMessage('History clicked');
    });

    const mcpServersCommand = vscode.commands.registerCommand('apps-copilot.mcpServers', () => {
        vscode.window.showInformationMessage('MCP Servers clicked');
    });

    const accountCommand = vscode.commands.registerCommand('apps-copilot.account', () => {
        vscode.window.showInformationMessage('Account clicked');
    });

    const settingsCommand = vscode.commands.registerCommand('apps-copilot.openSettings', () => {
        vscode.window.showInformationMessage('Settings clicked');
    });

    const closeCommand = vscode.commands.registerCommand('apps-copilot.close', () => {
        vscode.window.showInformationMessage('Close clicked');
    });

    context.subscriptions.push(
        undoCommand,
        redoCommand, 
        newChatCommand,
        historyCommand,
        mcpServersCommand,
        accountCommand,
        settingsCommand,
        closeCommand
    );
}

export function deactivate() {}
