var permissionSet = new Set();
$(document).ready(function(data) {
    $('.custom-control-label').click(function(){
        var permissionId = parseInt($(this).attr('value'));
        if (permissionSet.has(permissionId)) {
            permissionSet.delete(permissionId);
        }
        else {
            permissionSet.add(permissionId);
        }

        console.log(permissionSet);
    });
});

$("#save-permissions").click(function () {
    var roleId = 1;
    fetch("/save-permissions", {
        method: "POST",
        credentials: 'same-origin',
        headers: {
            "Content-Type": "application/json"
        },
        body: {
            roleId: roleId,
            permissionIds: JSON.stringify(Array.from(permissionSet))
        }
    }).then(function () {
        location.reload();
    });
});

