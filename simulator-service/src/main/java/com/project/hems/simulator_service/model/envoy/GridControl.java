package com.project.hems.simulator_service.model.envoy;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class GridControl {

    private Boolean allowImport;
    private Boolean allowExport;
    private Double maxImportW;
    private Double maxExportW;
}
