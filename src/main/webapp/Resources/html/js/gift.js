$gift_control = function () {
    var _entity;
    this.Init = function () {
        _entity = this;
        $("#btnAddgift").bind("click", function () {
            _entity.ShowDialog();
        })
    }
    this.ShowDialog = function () {
     
    }
}