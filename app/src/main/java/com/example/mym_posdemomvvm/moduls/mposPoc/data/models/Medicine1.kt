package com.example.mym_posdemomvvm.moduls.mposPoc.data.models

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "public.pe_catalog1")
//@Entity(tableName = "2021-10-21 02_30_57.688120")
data class Medicine1(

    @PrimaryKey @ColumnInfo(name = "product_ucode") var productUcode : String,
    @ColumnInfo(name = "product_mdm_uid") var productMdmUid : String,
    @ColumnInfo(name = "product_name") var productName : String,
    @ColumnInfo(name = "packform_name") var packformName : String,
    @ColumnInfo(name = "packe_quantity_value") var packeQuantityValue : Int = 1,
    @ColumnInfo(name = "measurement_unit") var measurementUnit : String,
    @ColumnInfo(name = "manufacturer") var manufacturer : String,
    @ColumnInfo(name = "manufacturer_id") var manufacturerId : String,
    @ColumnInfo(name = "composition") var composition : String,
    @ColumnInfo(name = "composition_id") var compositionId : Int,
    @ColumnInfo(name = "product_type") var productType : String,
    @ColumnInfo(name = "schedule") var schedule : String,
    @ColumnInfo(name = "product_hsn_code") var productHsnCode : Int = 99,
    @ColumnInfo(name = "cgst") var cgst : Int,
    @ColumnInfo(name = "sgst") var sgst : Int,
    @ColumnInfo(name = "igst") var igst : Int,
    @ColumnInfo(name = "gst") var gst : Int,
    @ColumnInfo(name = "product_is_banned") var productIsBanned : String,
    @ColumnInfo(name = "product_is_undeliverable") var productIsUndeliverable : String,
    @ColumnInfo(name = "product_is_discontinued") var productIsDiscontinued : String,
    @ColumnInfo(name = "is_live") var isLive : Int,
    @ColumnInfo(name = "product_substitute_group_id") var productSubstituteGroupId : String,
    @ColumnInfo(name = "product_is_generic") var productIsGeneric : String,
    @ColumnInfo(name = "product_variant_group_id") var productVariantGroupId : String,
    @ColumnInfo(name = "product_variant_group_variant_types") var productVariantGroupVariantTypes : String,
    @ColumnInfo(name = "product_is_refrigerated") var productIsRefrigerated : String,
    @ColumnInfo(name = "product_ml_disabled") var productMlDisabled : String,
    @ColumnInfo(name = "procurement_channel") var procurementChannel : String,
//    @ColumnInfo(name = "schedule_new") var scheduleNew : String,
//    @ColumnInfo(name = "is_h1") var isH1 : Int



)