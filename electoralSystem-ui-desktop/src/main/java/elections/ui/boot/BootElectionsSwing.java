package elections.ui.boot;

import elections.ui.service.IElectionsUI;

public class BootElectionsSwing extends AbstractBootElections {
	public static void main(String[] arguments) {
		new BootElectionsSwing().run();
	}

	@Override
	protected IElectionsUI getUI() {
		return ctx.getBean("electionsSwing", IElectionsUI.class);
	}
}
