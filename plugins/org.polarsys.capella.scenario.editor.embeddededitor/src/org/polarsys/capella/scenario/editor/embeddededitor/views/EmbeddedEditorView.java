/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.scenario.editor.embeddededitor.views;

import javax.inject.Inject;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyTitle;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.spelling.SpellingService;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditor;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.scenario.editor.EmbeddedEditorInstance;
import org.polarsys.capella.scenario.editor.dsl.ui.internal.DslActivator;
import org.polarsys.capella.scenario.editor.dsl.provider.TextualScenarioProvider;
import org.polarsys.capella.scenario.editor.embeddededitor.actions.XtextEditorActionFactory;
import org.polarsys.capella.scenario.editor.embeddededitor.commands.HelperCommands;
import org.polarsys.capella.scenario.editor.helper.EmbeddedEditorInstanceHelper;

import com.google.inject.Injector;

/*
 * EmbeddedEditorView will embed the xtext editor of the scenario
 */
public class EmbeddedEditorView extends ViewPart {
  private TabbedPropertyTitle scenarioTitle;

  /**
   * The ID of the view as specified by the extension.
   */
  public static final String ID = "org.eclipse.xtext.example.domainmodel.embeddededitor.views.EmbeddedEditorView";

  @Inject
  IWorkbench workbench;

  TextualScenarioProvider provider;

  class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
    @Override
    public String getColumnText(Object obj, int index) {
      return getText(obj);
    }

    @Override
    public Image getColumnImage(Object obj, int index) {
      return getImage(obj);
    }

    @Override
    public Image getImage(Object obj) {
      return workbench.getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
    }
  }

  @Override
  public void createPartControl(Composite parent) {
    makeActions();

    // Creates the title composite
    GridData titleLayoutData = new GridData(GridData.FILL_HORIZONTAL);
    // Do grab excess vertical space.
    titleLayoutData.grabExcessVerticalSpace = false;
    scenarioTitle = new TabbedPropertyTitle(parent, new TabbedPropertySheetWidgetFactory());
    scenarioTitle.setLayoutData(titleLayoutData);
    // Sets a default name with no image
    scenarioTitle.setTitle("Textual Editor", null);

    GridLayout layout = new GridLayout();
    // No blank space.
    layout.verticalSpacing = 0;
    layout.horizontalSpacing = 0;
    parent.setLayout(layout);

    DslActivator activator = DslActivator.getInstance();
    Injector injector = activator
        .getInjector(DslActivator.ORG_POLARSYS_CAPELLA_SCENARIO_EDITOR_DSL_TEXTUALSCENARIO);

    EditorsPlugin.getDefault().getPreferenceStore().setValue(SpellingService.PREFERENCE_SPELLING_ENABLED, false);
    provider = injector.getInstance(TextualScenarioProvider.class);
    EmbeddedEditorFactory factory = injector.getInstance(EmbeddedEditorFactory.class);
    
    EmbeddedEditor editor = factory.newEditor(provider).withParent(parent);
    editor.createPartialEditor();
    EmbeddedEditorInstance.setEmbeddedEditor(editor);
    
    // do a refresh of diagram and of the title bar
    HelperCommands.refreshTextEditor(this);
  }

  public TextualScenarioProvider getProvider() {
    return provider;
  }

  public void refreshTitleBar(String title) {
    if (!scenarioTitle.isDisposed()) {
      scenarioTitle.setRedraw(false);
      scenarioTitle.setTitle(title, null);
      scenarioTitle.setRedraw(true);
    }
  }

  private void makeActions() {
    XtextEditorActionFactory actionsFactory = new XtextEditorActionFactory();
    actionsFactory.createRefreshAction(this);
    actionsFactory.createSaveAction(this);
  }

  @Override
  public void setFocus() {
  }
  
  @Override
  public void init(IViewSite site) throws PartInitException {
    super.init(site);
    EmbeddedEditorInstanceHelper.setOpenedRepresentation();
  }
}
