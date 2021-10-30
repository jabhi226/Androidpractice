//package com.example.mym_posdemomvvm.models
//
//import androidx.room.Entity
//import androidx.room.ColumnInfo
//import java.util.*
//
//@Entity(tableName = "medicine")
//data class Medicine1(
//    var medicineId: String? = null,
//    var medicineName: String? = null,
//    var medicineShortCode: String? = null,
//    var mDMId: String? = null,
//    var barcode: String? = null,
//    var mdmMappingAttempted: Boolean? = null,
//    var mappingStatus: String? = null,
//    var mdmMappedBy: String? = null,
//    var mDMItemCode: String? = null,
//    var stock: Double? = 0.0,
//    private val mappingStage: String? = null,
//    var pastSales: Double? = 0.0,
//    var hsnCode: String? = null,
//    var medicinePackaging: String? = null,
////    private var manufacturer: Manufacturer? = null,
//    private var manufacturer: String? = null,
////    var generic: sun.net.www.content.text.Generic? = null,
//    var generic: String? = null,
////    var category: Category? = null,
//    var category: String? = null,
//    var hasBarcode: Boolean? = null,
//    var transported: Boolean? = null,
//    var therapeutic: String? = null,
//    var subtherapeutic: String? = null,
//    var indication: String? = null,
//    var rack: String? = null,
//    var minStock: Long? = null,
//    var maxStock: Long? = null,
//    @ColumnInfo(name = "medicine_base_name") var medicineBaseName: String? = null,
//    @ColumnInfo(name = "medicine_name_detailed") var medicineNameDetailed: String? = null,
//    @ColumnInfo(name = "medicine_name_no_space") var medicineNameNoSpace: String? = null,
//    var itemType: String? = null,
//    var src: String? = null,
//    var qualifier: String? = null,
//    var manufacturerName: String? = null,
//    var vat: Double = 0.0,
//    var igst: Double = 0.0,
//    var sgst: Double = 0.0,
//    var cgst: Double = 0.0,
//    var gccess: Double = 0.0,
//    @ColumnInfo(name = "disc_type") var discType: String? = null,
//    @ColumnInfo(name = "raw_id") var rawId: String? = null,
//    @ColumnInfo(name = "image_urls") var imageUrls: String? = null,
//    @ColumnInfo(name = "tcs_per") var tcsPer: Double = 0.0,
//    @ColumnInfo(name = "is_banned") var isBanned: Boolean = false,
//    @ColumnInfo(name = "is_undeliverable") var isUndeliverable: Boolean = false,
//    @ColumnInfo(name = "is_discontinued") var isDiscontinued: Boolean = false,
//    @ColumnInfo(name = "is_live") var isLive: Boolean = false,
//    @ColumnInfo(name = "generic_flag") var genericFlag: Boolean = false,
//    @ColumnInfo(name = "product_substitute_groupId") var productSubstituteGroupId: String? = null,
//    @ColumnInfo(name = "product_variant_group_variant_types") var productVariantGroupVariantTypes: String? = null,
//    @ColumnInfo(name = "product_variant_group_id") var productVariantGroupId: String? = null,
//    @ColumnInfo(name = "passive_scheme") var passiveScheme: Double? = null,
//    @ColumnInfo(name = "passive_mrp") var passiveMRP: Double? = null,
//    @ColumnInfo(name = "passive_cp") var passiveCP: Double? = null,
//    @ColumnInfo(name = "passive_stock") var passiveStock: Double? = null,
//    @ColumnInfo(name = "verified") var verified: Boolean = false,
//    @ColumnInfo(name = "isLoose") var loose: Boolean = false,
//    @ColumnInfo(name = "divisor") var divisor: Int = 1,
//
//    @ColumnInfo(name = "disc_per") var discPer: Double = 0.0,
//
//    @ColumnInfo(name = "incentive") var incentive: Double = 0.0,
//
//    @ColumnInfo(name = "partner_incentive")
//    var partnerIncentive: Double? = 0.0,
//
//    @ColumnInfo(name = "selling_price")
//    var sellingPrice: Double? = 0.0,
//
//    @ColumnInfo(name = "markup")
//    var markup: Double? = 0.0,
//
//    @ColumnInfo(name = "max_discount")
//    var maxDiscount: Double? = 0.0,
//
//    @ColumnInfo(name = "ded_per")
//    var dedPer: Double? = 0.0,
//
//    @ColumnInfo(name = "b2b_offset")
//    var offset: Double? = 0.0,
//
//    @ColumnInfo(name = "safety_stock_max")
//    var safetyStockMax: Int? = 0,
//
//    @ColumnInfo(name = "safety_stock_min")
//    var safetyStockMin: Int? = 0,
//
//    ) {
//
////    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
////    @LazyCollection(LazyCollectionOption.FALSE)
////    @Fetch(value = FetchMode.SUBSELECT)
////    @JsonManagedReference
////    var batches: List<Batch>? = null
//}
