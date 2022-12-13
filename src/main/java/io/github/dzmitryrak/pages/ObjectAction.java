package com.itechart.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ObjectAction extends BasePage {
    private final By FOLLOW_BUTTON = By.xpath("//span[@title='Follow']//ancestor::button");
    private final By FOLLOWING_BUTTON = By.xpath("//span[@title='Following']//ancestor::button");
    private final By MORE_ACTIONS = By.xpath(ACTIVE_TAB_LOCATOR + "//lightning-button-menu[contains(@class, 'menu-button-item')]");
    //There are 2 locators inside this one. For Ribbon and for button
    private final String RIBBON_ACTION_LOCATOR = ACTIVE_TAB_LOCATOR + "//runtime_platform_actions-ribbon-menu-item//span[text()='%s']|" +
            ACTIVE_TAB_LOCATOR + "//runtime_platform_actions-action-renderer//button[text()='%s']";

    @Step("Follow object")
    public DetailsPage follow() {
        clickJS(FOLLOW_BUTTON);
        $(FOLLOWING_BUTTON).shouldBe(visible);
        return new DetailsPage();
    }

    @Step("Unfollow object")
    public DetailsPage following() {
        clickJS(FOLLOWING_BUTTON);
        $(FOLLOW_BUTTON).shouldBe(visible);
        return new DetailsPage();
    }

    public NewObjectModal edit() {
        action("Edit");
        NewObjectModal editModal = new NewObjectModal();
        editModal.waitTillOpened();

        return editModal;
    }

    public void changeOwner() {
        action("Change Owner");
        //TODO implement code to return modal and make sure it is working
    }

    public void delete() {
        action("Delete");

        final By CONFIRMATION_MESSAGE = By.xpath("//*[contains(text(), 'Are you sure you want to delete this ')]");
        final By DELETE_MODAL_BUTTON = By.xpath("//div[@class='modal-container slds-modal__container']//button[@title= 'Delete']");
        final By SUCCESS_DELETE_MESSAGE = By.xpath("//*[contains(@class, 'slds-theme--success')]");


        $(DELETE_MODAL_BUTTON).shouldBe(visible);
        $(CONFIRMATION_MESSAGE).should(exist);
        $(DELETE_MODAL_BUTTON).click();
        $(SUCCESS_DELETE_MESSAGE).shouldBe(visible);
    }

    /**
     * Allows to click any action inside 'More Actions' ribbon or outside it at the details page
     * @param actionName
     */
    @Step("Click {actionName} action")
    public void action(String actionName) {
        $(By.xpath(String.format(RIBBON_ACTION_LOCATOR, actionName, actionName))).click();
    }

    /**
     * Clicks 'More Actions' ribbon
     * @return
     */
    @Step("Open additional actions")
    public ObjectAction moreActions() {
        clickJS(MORE_ACTIONS);
        return this;
    }
}
