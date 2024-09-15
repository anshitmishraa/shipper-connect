// Function to display a success message
function showSuccessMessage(message) {
    const successMessage = document.getElementById('successMessage');
    successMessage.textContent = message;
    successMessage.style.display = 'block';

    const errorMessage = document.getElementById('errorMessage');
    errorMessage.style.display = 'none';
}

// Function to display an error message
function showErrorMessage(message) {
    const errorMessage = document.getElementById('errorMessage');
    errorMessage.textContent = message;
    errorMessage.style.display = 'block';

    const successMessage = document.getElementById('successMessage');
    successMessage.style.display = 'none';
}

document.addEventListener('DOMContentLoaded', () => {
    // Get the current date
    var currentDate = new Date();

    // Format the current date as yyyy-mm-dd
    var formattedDate = currentDate.toISOString().substring(0, 10);

    // Set the value of the date input field to the current date
    document.getElementById('dateInput').value = formattedDate;

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
                    loadDate: date
                })
            })
            .then(response => response.text())
            .then(message => {
                // Display success message
            showSuccessMessage(`Load Added Successfully`);
            })
            .catch(error => {
                // Display error message
                showErrorMessage(error);
            });

        // Reset the form fields
        loadForm.reset();
    });

    const editLoadForm = document.getElementById('editLoadForm');

    editLoadForm.addEventListener('submit', (event) => {
        event.preventDefault();

        if (currentEditLoadId === null) {
            showErrorMessage('No load selected for editing.');
            return;
        }

        // Get the updated form values
        const updatedLoadingPoint = document.getElementById('editLoadingPointInput').value;
        const updatedUnloadingPoint = document.getElementById('editUnloadingPointInput').value;
        const updatedProductType = document.getElementById('editProductTypeInput').value;
        const updatedTruckType = document.getElementById('editTruckTypeInput').value;
        const updatedNoOfTrucks = document.getElementById('editNoOfTrucksInput').value;
        const updatedWeight = document.getElementById('editWeightInput').value;
        const updatedComment = document.getElementById('editCommentInput').value;
        const updatedShipperId = document.getElementById('editShipperIdInput').value;
        const updatedLoadDate = document.getElementById('editDateInput').value;
        const loadDate = new Date(updatedLoadDate);
        const formattedDate = loadDate.toISOString().split('T')[0];
        updatedLoadDate.value = formattedDate;

        fetch(`/loads/${currentEditLoadId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                loadingPoint: updatedLoadingPoint,
                unloadingPoint: updatedUnloadingPoint,
                productType: updatedProductType,
                truckType: updatedTruckType,
                noOfTrucks: updatedNoOfTrucks,
                weight: updatedWeight,
                comment: updatedComment,
                shipperId: updatedShipperId,
                loadDate: updatedLoadDate
            })
        })
        .then(response => response.text())
        .then(message => {
            // Display success message
            showSuccessMessage(`Load Updated Successfully`);

            // Close the edit modal
            editModal.classList.remove('is-active');
        })
        .catch(error => {
            // Display error message
            showErrorMessage(error);
        });

        // Reset the form fields
        editLoadForm.reset();

         currentEditLoadId = null; // Clear the current edit load ID
    });


    // Function to populate the edit modal with load data
    const populateEditModal = (loadId) => {
        // Fetch the load data by ID from the backend
        fetch(`/loads/${loadId}`)
            .then(response => response.json())
            .then(load => {
                // Populate the edit modal with the load data
                document.getElementById('editLoadingPointInput').value = load.loadingPoint;
                document.getElementById('editUnloadingPointInput').value = load.unloadingPoint;
                document.getElementById('editProductTypeInput').value = load.productType;
                document.getElementById('editTruckTypeInput').value = load.truckType;
                document.getElementById('editNoOfTrucksInput').value = load.noOfTrucks;
                document.getElementById('editWeightInput').value = load.weight;
                document.getElementById('editCommentInput').value = load.comment;
                document.getElementById('editShipperIdInput').value = load.shipperId;
                // Parse the date string to a Date object
                const loadDate = new Date(load.loadDate);
                // Format the date to 'YYYY-MM-DD' for the input field
                const formattedDate = loadDate.toISOString().split('T')[0];
                document.getElementById('editDateInput').value = formattedDate;

                currentEditLoadId = loadId; // Store the ID of the load being edited

                // Show the edit modal
                const editModal = document.getElementById('editModal');
                editModal.classList.add('is-active');
            })
            .catch(error => {
                console.error('Error fetching load data:', error);
            });
    };

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

        const dateCell = document.createElement('td');
        const loadDate = new Date(load.loadDate);
        if (!isNaN(loadDate.getTime())) {
            const formattedDate = loadDate.toLocaleDateString('en-US', {
                day: '2-digit',
                month: '2-digit',
                year: 'numeric'
            });

            dateCell.textContent = formattedDate;
        } else {
            dateCell.textContent = 'Invalid Date';
        }
        row.appendChild(dateCell);

        const actionsCell = document.createElement('td');
        const editButton = document.createElement('button');
        editButton.textContent = 'Edit';
        editButton.classList.add('button', 'is-small', 'is-primary', 'mr-2');
        editButton.addEventListener('click', () => {
            populateEditModal(load.id);
        });
        actionsCell.appendChild(editButton);

        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.classList.add('button', 'is-small', 'is-danger');
        deleteButton.addEventListener('click', () => {
            // Display a confirmation dialog
            if (confirm('Are you sure you want to delete this load?')) {
                // Fetch API DELETE request
                fetch(`/loads/${load.id}`, {
                        method: 'DELETE',
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    })
                    .then(response => {
                        if (response.ok) {
                            // Successful response
                            return response.json();
                        } else {
                            // Error response
                            return response.text().then(errorMessage => {
                                throw new Error(errorMessage);
                            });
                        }
                    })
                    .then(data => {
                        loadTableBody.removeChild(row);
                    })
                    .catch(error => {
                        showErrorMessage(error.message);
                    });
            }
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
            // Display error message
            showErrorMessage(error);
        });
});