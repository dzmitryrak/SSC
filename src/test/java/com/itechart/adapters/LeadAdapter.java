package com.itechart.adapters;

import com.itechart.models.Lead;
import com.itechart.models.ResponseStatus;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LeadAdapter extends BaseAdapter {

    public ResponseStatus create(Lead lead) {
        String response = post(API_BASE_URL + "/lead",
                jsonReader.toJson(lead), 201);
        return jsonReader.fromJson(response, ResponseStatus.class);
    }

    public void update(Lead lead, String leadId) {
        patch(API_BASE_URL + "/lead/" + leadId, jsonReader.toJson(lead), 204);
    }

    @Step("Get Lead")
    public Lead get(String leadId) {
        log.info("Getting Lead by Id: {}", leadId);
        String response = get(API_BASE_URL + "/lead/" + leadId, 200);
        return jsonReader.fromJson(response, Lead.class);
    }

    @Step("Delete Lead")
    public void delete(String leadId) {
        log.info("Deleting Lead by Id: {}", leadId);
        delete(API_BASE_URL + "/lead/" + leadId, 204);
    }
}
