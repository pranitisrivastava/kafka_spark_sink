package com.cv.polaris.mer.stream.model;

public class FunctionalException {
    private String corrupt_record;
    private String value;
    private String key;
    private String offset;
    private String job_id;
    private Integer job_sequence;
    private String job_scheduler;
    private String exception_id;
    private String tenant;
    private String business_category;
    private String business_subcategory;
    private String client_name;
    private String origin;
    private String component;
    private String sub_component;
    private String exception_type;
    private String exception_subtype;
    private String status;
    private String severity;
    private String owned_by;
    private String exception_message;
    private String exception_message_detail;
    private String exception_data;
    private Integer retry_count;
    private String exception_created_time;
    private String remediation_status;
    private String resolved_by;
    private String resolved_time;

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public Integer getJob_sequence() {
        return job_sequence;
    }

    public void setJob_sequence(Integer job_sequence) {
        this.job_sequence = job_sequence;
    }

    public String getJob_scheduler() {
        return job_scheduler;
    }

    public void setJob_scheduler(String job_scheduler) {
        this.job_scheduler = job_scheduler;
    }

    public String getException_id() {
        return exception_id;
    }

    public void setException_id(String exception_id) {
        this.exception_id = exception_id;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getBusiness_category() {
        return business_category;
    }

    public void setBusiness_category(String business_category) {
        this.business_category = business_category;
    }

    public String getBusiness_subcategory() {
        return business_subcategory;
    }

    public void setBusiness_subcategory(String business_subcategory) {
        this.business_subcategory = business_subcategory;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getSub_component() {
        return sub_component;
    }

    public void setSub_component(String sub_component) {
        this.sub_component = sub_component;
    }

    public String getException_type() {
        return exception_type;
    }

    public void setException_type(String exception_type) {
        this.exception_type = exception_type;
    }

    public String getException_subtype() {
        return exception_subtype;
    }

    public void setException_subtype(String exception_subtype) {
        this.exception_subtype = exception_subtype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getOwned_by() {
        return owned_by;
    }

    public void setOwned_by(String owned_by) {
        this.owned_by = owned_by;
    }

    public String getException_message() {
        return exception_message;
    }

    public void setException_message(String exception_message) {
        this.exception_message = exception_message;
    }

    public String getException_message_detail() {
        return exception_message_detail;
    }

    public void setException_message_detail(String exception_message_detail) {
        this.exception_message_detail = exception_message_detail;
    }

    public String getException_data() {
        return exception_data;
    }

    public void setException_data(String exception_data) {
        this.exception_data = exception_data;
    }

    public Integer getRetry_count() {
        return retry_count;
    }

    public void setRetry_count(Integer retry_count) {
        this.retry_count = retry_count;
    }

    public String getException_created_time() {
        return exception_created_time;
    }

    public void setException_created_time(String exception_created_time) {
        this.exception_created_time = exception_created_time;
    }

    public String getRemediation_status() {
        return remediation_status;
    }

    public void setRemediation_status(String remediation_status) {
        this.remediation_status = remediation_status;
    }

    public String getResolved_by() {
        return resolved_by;
    }

    public void setResolved_by(String resolved_by) {
        this.resolved_by = resolved_by;
    }

    public String getResolved_time() {
        return resolved_time;
    }

    public void setResolved_time(String resolved_time) {
        this.resolved_time = resolved_time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getCorrupt_record() {
        return corrupt_record;
    }

    public void setCorrupt_record(String corrupt_record) {
        this.corrupt_record = corrupt_record;
    }
}
