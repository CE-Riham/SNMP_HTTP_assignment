function extractData(row) {
  let arr = row.split(" ");
  let result = "";
  for (let i = 1; i < arr.length; i++) {
    result += arr[i] + " ";
  }
  if (arr.length === 1) result = "--";
  return result;
}

function callPhpFunction() {
  fetch("../php/getters.php?function=getTCPTable")
    .then((response) => response.text())
    .then((data) => {
      console.log(data);
      let updObject = JSON.parse(data);
      let numberOfRows = Object.keys(updObject).length / 5;
      for (var i = 0; i < numberOfRows; i++) {
        let tableRow = document.createElement("tr");

        let cell0 = document.createElement("td");
        cell0.innerHTML = i;
        let cell1 = document.createElement("td");
        let value = extractData(updObject[`${i}`]);
        cell1.innerText = value;

        let cell2 = document.createElement("td");
        value = extractData(updObject[`${i + numberOfRows}`]);
        cell2.innerText = value;

        let cell3 = document.createElement("td");
        value = extractData(updObject[`${i + 2 * numberOfRows}`]);
        cell3.innerText = value;

        let cell4 = document.createElement("td");
        value = extractData(updObject[`${i + 3 * numberOfRows}`]);
        cell4.innerText = value;

        let cell5 = document.createElement("td");
        value = extractData(updObject[`${i + 4 * numberOfRows}`]);
        cell5.innerText = value;

        tableRow.appendChild(cell0);
        tableRow.appendChild(cell1);
        tableRow.appendChild(cell2);
        tableRow.appendChild(cell3);
        tableRow.appendChild(cell4);
        tableRow.appendChild(cell5);
        document.getElementById("systemGroupTable").appendChild(tableRow);
      }
    })
    .catch((error) => console.error("Request failed:", error));
}
callPhpFunction();
