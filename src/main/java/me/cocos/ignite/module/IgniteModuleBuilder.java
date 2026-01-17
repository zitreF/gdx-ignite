package me.cocos.ignite.module;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.util.IconLoader;
import me.cocos.ignite.model.ProjectConfig;
import me.cocos.ignite.service.GeneratorService;
import me.cocos.ignite.ui.IgniteWizardStep;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class IgniteModuleBuilder extends ModuleBuilder {

    private static final Icon IGNITE_ICON = IconLoader.getIcon("/META-INF/pluginIcon.svg", IgniteModuleBuilder.class);

    @Override
    public Icon getNodeIcon() {
        return IGNITE_ICON;
    }

    @Override
    public String getPresentableName() {
        return "LibGDX Project";
    }

    @Override
    public String getDescription() {
        return "Modern LibGDX project generator supporting many platforms (Desktop, Android, iOS, HTML).";
    }

    @Override
    public String getGroupName() {
        return "LibGDX";
    }

    @Override
    public ModuleType<?> getModuleType() {
        return ModuleType.EMPTY;
    }

    @Override
    public @Nullable ModuleWizardStep getCustomOptionsStep(WizardContext context, Disposable parentDisposable) {
        return new IgniteWizardStep(context);
    }

    // TODO: Add 3rd party libraries
//    @Override
//    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext, @NotNull ModulesProvider modulesProvider) {
//        return new ModuleWizardStep[]{new IgniteLibrariesWizardStep(wizardContext)};
//    }

    @Override
    public void setupRootModel(@NotNull ModifiableRootModel modifiableRootModel) {
        this.doConfiguration(modifiableRootModel);
    }

    private void doConfiguration(ModifiableRootModel model) {
        ProjectConfig config = IgniteWizardStep.getLastConfig();
        if (config == null) return;

        if (config.sdk() != null) {
            model.setSdk(config.sdk());
        }

        GeneratorService generator = new GeneratorService();
        ProjectConfig finalConfig = new ProjectConfig(
                config.gameName(),
                config.packageName(),
                config.mainClass(),
                model.getProject().getBasePath(),
                config.sdk(),
                config.modules()
        );

        generator.generate(finalConfig);
    }
}
