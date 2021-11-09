package com.example.mym_posdemomvvm.models

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "public.pe_catalog")
data class Medicine1(
    @PrimaryKey
    @ColumnInfo(name = "medicine_id")
    var medicineId: String = "",

    @ColumnInfo(name = "medicine_name")
    var medicineName: String = "",

    @ColumnInfo(name = "medicine_short_code")
    var medicineShortCode: String = "null",

    @ColumnInfo(name = "mdm_id")
    var mDMId: String = "null",

    @ColumnInfo(name = "barcode")
    var barcode: String = "null",

    @ColumnInfo(name = "mdm_mapping_attempted")
    var mdmMappingAttempted: Boolean = false,

    @ColumnInfo(name = "mapping_status")
    var mappingStatus: String = "null",

    @ColumnInfo(name = "mdm_mapped_by")
    var mdmMappedBy: String = "null",

    @ColumnInfo(name = "mdm_itemcode")
    var mDMItemCode: String = "null",

    var stock: Double = 0.0,

    val mappingStage: String = "null",

    var pastSales: Double = 0.0,

    @ColumnInfo(name = "hsn_code")
    var hsnCode: String = "null",


    @ColumnInfo(name = "medicine_packaging")
    var medicinePackaging: String = "null",
//
//    private var manufacturer: Manufacturer? = null,
    @ColumnInfo(name = "manufacturer_id")
    var manufacturer: String = "null",
//
//    var generic: sun.net.www.content.text.Generic? = null,
    @ColumnInfo(name = "generic_id")
    var generic: String = "null",
//
//    var category: Category? = null,
    @ColumnInfo(name = "category_id")
    var category: String = "null",

    @ColumnInfo(name = "hasBarcode")
    var hasBarcode: Boolean = false,

    @ColumnInfo(name = "transported")
    var transported: Boolean = false,

    @ColumnInfo(name = "therapeutic")
    var therapeutic: String = "null",

    @ColumnInfo(name = "subtherapeutic")
    var subtherapeutic: String = "null",

    @ColumnInfo(name = "indication")
    var indication: String = "null",

    @ColumnInfo(name = "rack")
    var rack: String = "null",

    @ColumnInfo(name = "minStock")
    var minStock: Long = 0,

    @ColumnInfo(name = "maxStock")
    var maxStock: Long = 0,

    @ColumnInfo(name = "medicine_base_name")
    var medicineBaseName: String = "null",

    @ColumnInfo(name = "medicine_name_detailed")
    var medicineNameDetailed: String = "null",

    @ColumnInfo(name = "medicine_name_no_space")
    var medicineNameNoSpace: String = "null",

    var itemType: String = "null",

    var src: String = "null",

    var qualifier: String = "null",

    var manufacturerName: String = "null",

    var vat: Double = 0.0,

    var igst: Double = 0.0,

    var sgst: Double = 0.0,

    var cgst: Double = 0.0,

    var gccess: Double = 0.0,

    @ColumnInfo(name = "disc_type")
    var discType: String = "null",

    @ColumnInfo(name = "raw_id")
    var rawId: String = "null",

    @ColumnInfo(name = "image_urls")
    var imageUrls: String = "null",

    @ColumnInfo(name = "tcs_per") var tcsPer: Double = 0.0,

    @ColumnInfo(name = "is_banned") var isBanned: Boolean = false,

    @ColumnInfo(name = "is_undeliverable") var isUndeliverable: Boolean = false,

    @ColumnInfo(name = "is_discontinued") var isDiscontinued: Boolean = false,

    @ColumnInfo(name = "is_live") var isLive: Boolean = false,

    @ColumnInfo(name = "generic_flag") var genericFlag: Boolean = false,

    @ColumnInfo(name = "product_substitute_groupId") var productSubstituteGroupId: String = "null",

    @ColumnInfo(name = "product_variant_group_variant_types") var productVariantGroupVariantTypes: String = "null",

    @ColumnInfo(name = "product_variant_group_id")
    var productVariantGroupId: String = "null",

    @ColumnInfo(name = "passive_scheme")
    var passiveScheme: Double = 0.0,

    @ColumnInfo(name = "passive_mrp") var passiveMRP: Double = 0.0,

    @ColumnInfo(name = "passive_cp") var passiveCP: Double = 0.0,

    @ColumnInfo(name = "passive_stock") var passiveStock: Double = 0.0,

    @ColumnInfo(name = "verified")
    var verified: Boolean = false,

    @ColumnInfo(name = "isLoose") var loose: Boolean = false,

    @ColumnInfo(name = "divisor") var divisor: Int = 1,

    @ColumnInfo(name = "disc_per") var discPer: Double = 0.0,

    @ColumnInfo(name = "incentive")
    var incentive: Double = 0.0,

    @ColumnInfo(name = "partner_incentive")
    var partnerIncentive: Double = 0.0,

    @ColumnInfo(name = "selling_price")
    var sellingPrice: Double = 0.0,

    @ColumnInfo(name = "markup")
    var markup: Double = 0.0,

    @ColumnInfo(name = "max_discount")
    var maxDiscount: Double = 0.0,

    @ColumnInfo(name = "ded_per")
    var dedPer: Double = 0.0,

    @ColumnInfo(name = "b2b_offset")
    var offset: Double = 0.0,

    @ColumnInfo(name = "safety_stock_max")
    var safetyStockMax: Int = 0,

    @ColumnInfo(name = "safety_stock_min")
    var safetyStockMin: Int = 0,

    ) {

//    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @Fetch(value = FetchMode.SUBSELECT)
//    @JsonManagedReference
//    var batches: List<Batch>? = null
}
