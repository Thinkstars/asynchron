$(document).ready(function () {
    selectTyp();
});

function calculateAmount(multiplicator) {
    $.get("/calculateamount", {
        multiplicator: multiplicator,
        calculationTyp: $('#expenseTyp option:selected').val()
    }, function (data) {
        $('#amount').val(data.amount);
    })
}

function selectTyp() {
    var selectedElement = $('#expenseTyp option:selected').val();

    if (selectedElement === 'PER_DIEMS') {
        $('#distanceDiv').addClass("d-none");
        $('#dayRangeDiv').removeClass("d-none");
    } else {
        $('#distanceDiv').removeClass("d-none");
        $('#dayRangeDiv').addClass("d-none");
    }

}