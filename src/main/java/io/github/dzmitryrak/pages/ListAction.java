package io.github.dzmitryrak.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ListAction extends BasePage {

    //TODO find MORE ACTIONS locator after adding more actions to list view
    private final By MORE_ACTIONS = By.xpath(ACTIVE_TAB_LOCATOR + "//lightning-button-menu[contains(@class, 'menu-button-item')]");
    //TODO Add more actons to page so Ribbon will appear and change locator
    private final String RIBBON_ACTION_LOCATOR = ACTIVE_TAB_LOCATOR + "//ul[contains(@class, 'oneActionsRibbon')]//a[title()='%s']";

    public NewObjectModal newObject() {
        action("New");
        NewObjectModal editModal = new NewObjectModal();
        editModal.waitTillOpened();

        return editModal;
    }

    /**
     * Allows to click any action inside 'More Actions' ribbon or outside it at the details page
     * @param actionName
     */
    @Step("Click {actionName} action")
    public void action(String actionName) {
        $(By.xpath(String.format(RIBBON_ACTION_LOCATOR, actionName))).click();
    }

    /**
     * Locator should be updated before use
     * @return
     */
    @Deprecated
    @Step("Open additional actions")
    public ListAction moreActions() {
        clickJS(MORE_ACTIONS);
        return this;
    }
}
