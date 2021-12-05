$('#loadUsers').click(() => {
    $('.users-container').empty();

    fetch('http://localhost:8080/users') // Fetch the data (GET request)
        .then((response) => response.json()) // Extract the JSON from the Response
        .then((json) => json.forEach((user) => { // Render the JSON data to the HTML
            let tableRow =
                '<tr>' +
                '<td>' + user.id + '</td>' +
                '<td>' + user.username + '</td>' +
                '<td>' + user.firstName + '</td>' +
                '<td>' + user.lastName + '</td>' +
                '<td>' + user.email + '</td>' +
                '<td>' +
                '<button class="delete-btn" data-user-id="' + user.id +  '">Delete</button>' +
                '</td>' +
                '</tr>'

            $('.users-container').append(tableRow);
        }));
});

$('body').on('click', 'button.delete-btn', function() {
    let userId = $(this).data('user-id');
    console.log("Author ID is " + userId)

    fetch('http://localhost:8080/users/' + userId, {
        method: 'DELETE'
    })
        .then(() => {
            console.log("Author deleted!")
            $('.users-container').empty()
            $('#loadUsers').click();
        });
});
