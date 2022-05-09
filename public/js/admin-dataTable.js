/* Formatting function for row details - modify as you need */
function format(Product) {
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">' +
        '<tr>' +
        '<td>référence produit:</td>' +
        '<td>' + Product.productname + '</td>' +
        '</tr>' +
        '<tr>' +
        '<td>prix du produit:</td>' +
        '<td>' + Product.productprice + '</td>' +
        '</tr>' +
        '<tr>' +
        '<td>quantite du produit:</td>' +
        '<td>' + Product.productQuantity + '</td>' +
        '</tr>' +
        '<tr>' +
        '<td>image produit:</td>' +
        '<td>' +'<img width="60px" height="60px" src="/img/products/'+Product.productimage+'" /> </td>' +
        '</tr>' +
        '</table>';
}

$(document).ready(function () {
    var table = $('#example').DataTable({
        "ajax": "/getAllCommands",
        "columns": [
            {
                "className": 'dt-control',
                "orderable": false,
                "data": null,
                "defaultContent": ''
            },
            {"data": "userFullName"},
            {"data": "etatCommande"},
            {"data": "totale"},
            {
                "data": null,
                render: function (data, type, full) {
                    return "<a href=validCommande/"+ data.commandeId + ">" +
                        "<button type='button' class='btn btn-success'>Valider</button>" +
                        "&nbsp<a href=refuseCommand/"+ data.commandeId + ">" +
                        "<button type='button' class='btn btn-danger'>Refuse</button></a>"
                }
            }
        ],
        "order": [[1, 'asc']]
    });

    // Add event listener for opening and closing details
    $('#example tbody').on('click', 'td.dt-control', function () {
        var tr = $(this).closest('tr');
        var row = table.row(tr);

        if (row.child.isShown()) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        } else {
            // Open this row
            row.child(format(row.data())).show();
            tr.addClass('shown');
        }
    });
});