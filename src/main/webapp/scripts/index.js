    $(document).ready(function () {
        getTable()
    })

    function validate() {
        const task =$('#newTask')
        if (task.val() === '') {
            alert("Not filled: " + task.attr("id"));
            return false;
        }
        return true;
    }

    function getTable() {
        let tableMode;
        if ($("#showAll").is(':checked')){
            tableMode = "checked";
        } else {
            tableMode = "unchecked";
        }
        $.ajax({
            type: "GET",
            url: 'http://localhost:8080/todolist/index.do',
            data: {
                tableMode : tableMode
            },
            dataType: 'json'
        }).done(function (data) {
            $("#table").empty();
            let table = $('table')
            data.forEach(function (item, i) {
                table.append('<tr>'
                    + '<td>' + item.description + '</td>'
                    + '<td>' + item.created + '</td>'
                    + '<td>' +item.user.name + '</td>'
                    + '<td>'
                    + '<input type="checkbox"'
                    + (item.done ? ' checked="checked" ' : "")
                    + 'id="' + item.id + '"'
                    + ' onclick="updateTask(this.id);">'
                    + '</td>'
                    + '</tr>');
            })
        }).fail(function (err) {
            alert(err);
        });
    }

    function addNewTask() {
    if (validate()) {
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/todolist/index.do',
                data: {
                    description : $("#newTask").val(),
                },
                dataType: 'json',
            }).done(function (data) {
                getTable();
                return true;
            }).fail(function (err) {
                alert(err);
            });
        }
    }

    function updateTask(id) {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/todolist/update',
            data:  {
                id : id
            },
            dataType: 'json',
        }).done(function () {
            getTable();
            return true;
        }).fail(function (err) {
            alert(err);
        });

}