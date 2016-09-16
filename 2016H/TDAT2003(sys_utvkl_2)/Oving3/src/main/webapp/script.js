/**
 * Created by asdfLaptop on 16.09.2016.
 */
$(document).ready(function(){
    // Bind opp tabellen mot rest-ressursen '/kunder'
    $('#myTable').DataTable( {
        ajax: {
            url: 'rest/kunder',
            dataSrc: ''
        },
        columns: [
            { data: 'id' },
            { data: 'name' }
        ]
    });

    // Slett rest-ressursen '/kunder/kundeId'
    $("#delete").click(function () {
        $.ajax({
            url: 'rest/kunder/' + $("#deleteId").val(),
            type: 'DELETE',
            success: function(result) {
                $('#myTable').DataTable().ajax.reload();
            }
        });
    });

    // Rediger rest-ressursen '/kunder/kundeId'
    $("#edit").click(function () {
        $.ajax({
            url: 'rest/kunder/' + $("#editId").val(),
            type: 'PUT',
            data: JSON.stringify({
                id: $("#editId").val(),
                name: $("#editName").val(),
            }),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function(result) {
                $('#myTable').DataTable().ajax.reload();
            }
        });
    });

    // Lag ny rest-ressursen under '/kunder/'
    $("#create").click(function () {
        $.ajax({
            url: 'rest/kunder',
            type: 'POST',
            data: JSON.stringify({
                id: $("#newId").val(),
                name: $("#newName").val(),
            }),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function(result) {
                $('#myTable').DataTable().ajax.reload();
            }
        });
    });
});