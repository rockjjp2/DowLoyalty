$(function () {
    page.$Init();
});
var page = {
    $Init: function () {
        $("#Retailers").bind("click", function () {
            $("#layer").css("display", "block");
        })
        $("#close").bind("click", function () {
            $("#layer").css("display", "none");
        })
        $("#Person_Submit").bind("click", function () {
            var val_payPlatform = $('input[name="radio_Retailers"]:checked ').val();
            var dataId = $('input[name="radio_Retailers"]:checked ').attr("data-id");
            $("#retailerSelect option").remove();
			$("#retailerSelect").append("<option value='"+dataId+"'>"+val_payPlatform+"</option>");
            $("#lblRetailers").html(val_payPlatform);
            $("#lblRetailers").attr("sid",dataId);
            $("#layer").css("display", "none");
        })
        $("#People_Content tr").bind("click", function () {
           // alert($(this).find('input[name="radio_Retailers"]').val());
            $(this).find('input[name="radio_Retailers"]').attr("checked", true);
        })
    }
}