package com.project.hems.dispatch_manager_service.service;


import com.project.hems.hems_api_contracts.contract.dispatch.DispatchEventDto;

public interface DispatchCommandProducer {
     void processBulkDispatchEvent(DispatchEventDto bulkEvent);
}
