package de.nordakademie.wpk.team2.car2go.product;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.ActionSetRegistry;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;

@SuppressWarnings("restriction")
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "de.nordakademie.wpk.product.perspective";

	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

	@Override
	public void initialize(IWorkbenchConfigurer configurer) {
		super.initialize(configurer);
		
		// For standalone app, remove the stuff we don't use
		// from: http://dev.eclipse.org/newslists/news.eclipse.platform.rcp/msg28049.html
        ActionSetRegistry reg = WorkbenchPlugin.getDefault()
                .getActionSetRegistry();

        IActionSetDescriptor[] actionSets = reg.getActionSets();
        String[] removeActionSets = new String[] {
            "org.eclipse.search.searchActionSet",
            "org.eclipse.ui.cheatsheets.actionSet",
            "org.eclipse.ui.edit.text.actionSet.navigation",
            "org.eclipse.ui.edit.text.actionSet.annotationNavigation",
            "org.eclipse.ui.edit.text.actionSet.convertLineDelimitersTo",
            "org.eclipse.ui.externaltools.ExternalToolsSet",
            "org.eclipse.ui.WorkingSetActionSet",
            "org.eclipse.update.ui.softwareUpdates", };

        for (int i = 0; i < actionSets.length; i++)
        {
            boolean found = false;
            for (int j = 0; j < removeActionSets.length; j++)
            {
                if (removeActionSets[j].equals(actionSets[i].getId()))
                    found = true;
            }

            if (!found)
                continue;
            IExtension ext = actionSets[i].getConfigurationElement()
                    .getDeclaringExtension();
            reg.removeExtension(ext, new Object[] { actionSets[i] });
        }
	}

}
