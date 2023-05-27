$(document).ready(function () {
    selectTyp();
});

function calculateAmount() {
    if ($('#expenseType option:selected').val() === 'PER_DIEMS') {
        sendRestRequest(calculateDayDifference());
    } else {
        sendRestRequest($('#distance').val());
    }
}

function calculateDayDifference() {
    let dateFrom = new Date($('#dayRange').val());
    let dateTo = new Date($('#dayRangeTo').val());

    return Math.ceil((dateTo - dateFrom) / (1000 * 3600 * 24));
}

function sendRestRequest(multiplicator) {
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
        $('#dayRangeToDiv').removeClass("d-none");
    } else {
        $('#distanceDiv').removeClass("d-none");
        $('#dayRangeToDiv').addClass("d-none");
    }

}