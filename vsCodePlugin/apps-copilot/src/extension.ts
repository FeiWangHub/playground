import * as vscode from 'vscode';

// 树视图项目类
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

// 树视图数据提供者
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
            // 根级别的应用
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

export function activate(context: vscode.ExtensionContext) {
    console.log('Congratulations, your extension "apps-copilot" is now active!');

    // 创建树视图提供者
    const appsProvider = new AppsProvider();
    
    // 注册树视图
    vscode.window.createTreeView('appsExplorer', {
        treeDataProvider: appsProvider,
        showCollapseAll: true
    });

    // 注册命令
    const helloWorldCommand = vscode.commands.registerCommand('apps-copilot.helloWorld', () => {
        vscode.window.showInformationMessage('Hello World from Apps Copilot!');
    });

    const refreshCommand = vscode.commands.registerCommand('apps-copilot.refreshApps', () => {
        appsProvider.refresh();
        vscode.window.showInformationMessage('Apps refreshed!');
    });

    context.subscriptions.push(helloWorldCommand, refreshCommand);
}

export function deactivate() {}
