$(document).ready(function () {
    selectTyp();
});

function calculateAmount(multiplicator) {
    $.get("/calculateamount", {
        multiplicator: multiplicator,
        calculationTyp: $('#expenseType option:selected').val()
    }).done(function (data) {
        $('#amount').val(data.amount);
    }).fail(function () {
        alert("Ups, something went wrong!");
    });
}

function selectTyp() {
    var selectedElement = $('#expenseType option:selected').val();

    if (selectedElement === 'PER_DIEMS') {
        $('#distanceDiv').addClass("d-none");
        $('#dayRangeDiv').removeClass("d-none");
    } else {
        $('#distanceDiv').removeClass("d-none");
        $('#dayRangeDiv').addClass("d-none");
    }

}