/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 *  
 *  This program and the accompanying materials are made available under the
 *  terms of the Eclipse Public License 2.0 which is available at
 *  http://www.eclipse.org/legal/epl-2.0
 *  
 *  SPDX-License-Identifier: EPL-2.0
 *  
 *  Contributors:
 *     Thales - initial API and implementation
 ******************************************************************************/
package org.polarsys.capella.scenario.editor.ju;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.scenario.editor.ju.testcases.ArmTimerMessagesFromXtextToDiagramTestCase;
import org.polarsys.capella.scenario.editor.ju.testcases.CreateDeleteMessagesFromXtextToDiagramTestCase;
import org.polarsys.capella.scenario.editor.ju.testcases.DeleteCombinedFragmentFromXtextToDiagramTestCase;
import org.polarsys.capella.scenario.editor.ju.testcases.DeleteOperandsXtextToDiagramTestCase;
import org.polarsys.capella.scenario.editor.ju.testcases.DeleteStateFragmentFromXtextToDiagramTestCase;
import org.polarsys.capella.scenario.editor.ju.testcases.DuplicatedCombinedFragmentsAndOperandsXtextToDiagramTestCase;
import org.polarsys.capella.scenario.editor.ju.testcases.DuplicatedMessagesFromXtextToDiagramTestCase;
import org.polarsys.capella.scenario.editor.ju.testcases.ExchangesAndInstanceRolesXtextToDiagramTestCase;
import org.polarsys.capella.scenario.editor.ju.testcases.LostAndFoundXtextToDiagramTestCase;
import org.polarsys.capella.scenario.editor.ju.testcases.ReferencesXtextToDiagramTestCase;
import org.polarsys.capella.scenario.editor.ju.testcases.ReloadDataFromDiagramToTextTestCase;
import org.polarsys.capella.scenario.editor.ju.testcases.RolesXtextToDiagramTestCase;
import org.polarsys.capella.scenario.editor.ju.testcases.SaveDataFromXtextToDiagramTestCase;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class ScenarioEditorTestSuite extends BasicTestSuite {

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new ScenarioEditorTestSuite();
  }
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("TextualEditor");
  }

  /**
   * @see org.polarsys.capella.test.framework.api.BasicTestSuite#getTests()
   */
  @Override
  protected List<BasicTestArtefact> getTests() {
    List<BasicTestArtefact> tests = new ArrayList<BasicTestArtefact>();
    tests.add(new ReloadDataFromDiagramToTextTestCase());
    tests.add(new SaveDataFromXtextToDiagramTestCase());
    tests.add(new ArmTimerMessagesFromXtextToDiagramTestCase());
    tests.add(new CreateDeleteMessagesFromXtextToDiagramTestCase());
    tests.add(new DeleteCombinedFragmentFromXtextToDiagramTestCase());
    tests.add(new DeleteStateFragmentFromXtextToDiagramTestCase());
    tests.add(new DuplicatedMessagesFromXtextToDiagramTestCase());
    tests.add(new DuplicatedCombinedFragmentsAndOperandsXtextToDiagramTestCase());
    tests.add(new DeleteOperandsXtextToDiagramTestCase());
    tests.add(new RolesXtextToDiagramTestCase());
    tests.add(new ExchangesAndInstanceRolesXtextToDiagramTestCase());
    tests.add(new LostAndFoundXtextToDiagramTestCase());
    tests.add(new ReferencesXtextToDiagramTestCase());
    return tests;
  }
}
