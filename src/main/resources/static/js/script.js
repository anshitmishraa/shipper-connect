document.addEventListener('DOMContentLoaded', () => {
    const loadForm = document.getElementById('loadForm');

    loadForm.addEventListener('submit', (event) => {
        event.preventDefault();

        const loadingPoint = document.getElementById('loadingPointInput').value;
        const unloadingPoint = document.getElementById('unloadingPointInput').value;
        const productType = document.getElementById('productTypeInput').value;
        const truckType = document.getElementById('truckTypeInput').value;
        const noOfTrucks = document.getElementById('noOfTrucksInput').value;
        const weight = document.getElementById('weightInput').value;
        const comment = document.getElementById('commentInput').value;
        const shipperId = document.getElementById('shipperIdInput').value;
        const date = document.getElementById('dateInput').value;

        // Perform your logic to send the data to the server or perform any other operations
        // For example, you can use the Fetch API to send a POST request to your backend

        fetch('/loads', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    loadingPoint,
                    unloadingPoint,
                    productType,
                    truckType,
                    noOfTrucks,
                    weight,
                    comment,
                    shipperId,
                    date
                })
            })
            .then(response => response.text())
            .then(message => {
                console.log(message);
                // Handle the response from the server as needed
                // You can display a success message or perform any other actions
            })
            .catch(error => {
                console.error('Error:', error);
                // Handle any errors that occurred during the request
                // You can display an error message or perform any other actions
            });

        // Reset the form fields
        loadForm.reset();
    });

    const loadForm = document.getElementById('loadForm');
    const loadTableBody = document.getElementById('loadTableBody');

    // Function to create a table row for a load
    const createLoadTableRow = (load) => {
        const row = document.createElement('tr');

        const idCell = document.createElement('td');
        idCell.textContent = load.id;
        row.appendChild(idCell);

        const loadingPointCell = document.createElement('td');
        loadingPointCell.textContent = load.loadingPoint;
        row.appendChild(loadingPointCell);

        const unloadingPointCell = document.createElement('td');
        unloadingPointCell.textContent = load.unloadingPoint;
        row.appendChild(unloadingPointCell);

        const productTypeCell = document.createElement('td');
        productTypeCell.textContent = load.productType;
        row.appendChild(productTypeCell);

        const actionsCell = document.createElement('td');
        const editButton = document.createElement('button');
        editButton.textContent = 'Edit';
        editButton.classList.add('button', 'is-small', 'is-primary', 'mr-2');
        editButton.addEventListener('click', () => {
            // Handle edit functionality here
            // You can redirect to a separate page or show a modal to edit the load details
            // For simplicity, this example doesn't include the complete edit implementation
            console.log('Edit load with ID:', load.id);
        });
        actionsCell.appendChild(editButton);

        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.classList.add('button', 'is-small', 'is-danger');
        deleteButton.addEventListener('click', () => {
            // Handle delete functionality here
            // For simplicity, this example only removes the table row from the DOM
            loadTableBody.removeChild(row);
            console.log('Delete load with ID:', load.id);
        });
        actionsCell.appendChild(deleteButton);

        row.appendChild(actionsCell);

        return row;
    };

    // Fetch load data from the backend
    fetch('/loads')
        .then(response => response.json())
        .then(data => {
            // Once the data is retrieved, populate the load table
            data.forEach(load => {
                const loadTableRow = createLoadTableRow(load);
                loadTableBody.appendChild(loadTableRow);
            });
        })
        .catch(error => {
            console.error('Error fetching load data:', error);
        });
});