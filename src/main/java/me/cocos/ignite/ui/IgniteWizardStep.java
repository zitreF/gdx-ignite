package me.cocos.ignite.ui;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.roots.ui.configuration.SdkComboBox;
import com.intellij.openapi.roots.ui.configuration.SdkComboBoxModel;
import com.intellij.openapi.roots.ui.configuration.projectRoot.ProjectSdksModel;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import com.intellij.util.ui.JBUI;
import me.cocos.ignite.model.ModuleType;
import me.cocos.ignite.model.ProjectConfig;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class IgniteWizardStep extends ModuleWizardStep {

    private static ProjectConfig lastConfig;

    private final JBTextField gameNameField;
    private final JBTextField packageField;
    private final JBTextField mainClassField;
    private final SdkComboBox sdkComboBox;

    private final JBCheckBox desktopBox;
    private final JBCheckBox androidBox;
    private final JBCheckBox iosBox;
    private final JBCheckBox htmlBox;

    private final JPanel mainPanel;

    public IgniteWizardStep() {
        this.gameNameField = new JBTextField("MyAwesomeGame");
        this.packageField = new JBTextField("me.cocos.game");
        this.mainClassField = new JBTextField("Main");

        this.sdkComboBox = this.createSdkComboBox();

        this.desktopBox = new JBCheckBox("Desktop (LWJGL3)", true);
        this.androidBox = new JBCheckBox("Android");
        this.iosBox = new JBCheckBox("IOS (RoboVM)");
        this.htmlBox = new JBCheckBox("HTML (TeaVM)");

        this.mainPanel = this.buildUI();
    }

    private SdkComboBox createSdkComboBox() {
        ProjectSdksModel sdksModel = new ProjectSdksModel();

        Project project = ProjectManager.getInstance().getDefaultProject();

        sdksModel.reset(project);

        SdkComboBoxModel model = SdkComboBoxModel.createJdkComboBoxModel(project, sdksModel);

        return new SdkComboBox(model);
    }

    private JPanel buildUI() {
        JPanel modulePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        modulePanel.setBorder(JBUI.Borders.empty(10, 0));
        modulePanel.add(desktopBox);
        modulePanel.add(androidBox);
        modulePanel.add(iosBox);
        modulePanel.add(htmlBox);

        return FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Project name:"), gameNameField, 1, false)
                .addLabeledComponent(new JBLabel("JDK:"), sdkComboBox, 1, false)
                .addSeparator()
                .addLabeledComponent(new JBLabel("Package:"), packageField, 1, false)
                .addLabeledComponent(new JBLabel("Main class:"), mainClassField, 1, false)
                .addSeparator()
                .addComponent(new JBLabel("Platforms:"), 5)
                .addComponent(modulePanel)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    @Override
    public JComponent getComponent() {
        return mainPanel;
    }

    @Override
    public void updateDataModel() {
        Set<ModuleType> selectedModules = new HashSet<>();
        if (desktopBox.isSelected()) selectedModules.add(ModuleType.DESKTOP);
        if (androidBox.isSelected()) selectedModules.add(ModuleType.ANDROID);
        if (iosBox.isSelected()) selectedModules.add(ModuleType.IOS);
        if (htmlBox.isSelected()) selectedModules.add(ModuleType.HTML);

        IgniteWizardStep.lastConfig = new ProjectConfig(
                gameNameField.getText(),
                packageField.getText(),
                mainClassField.getText(),
                "",
                sdkComboBox.getSelectedSdk(),
                selectedModules
        );
    }

    public static ProjectConfig getLastConfig() {
        return lastConfig;
    }
}