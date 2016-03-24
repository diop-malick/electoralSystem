package elections.client.boot;

import elections.client.ui.IElectionsUI;

public class BootElectionsConsole extends AbstractBootElections{
	public static void main(String[] arguments) {
		new BootElectionsConsole().run();
	}

	@Override
	protected IElectionsUI getUI() {
		return ctx.getBean("electionsConsole",IElectionsUI.class);
	}
}
