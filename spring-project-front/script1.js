function getChampionStatsTable(){
    let url = `http://localhost:8080/api/v1/match/championStats/Skilled%20Teaser`;
    return fetch(url)
    .then(response => response.json())
    .then(data => {
            let table = `<tr>
            <th>Win Ratio</th>
            <th>Champion Name</th>
            <th>CS</th>
            <th>Played Matches</th>
            <th>Kills</th>
            <th>Deats</th>
            <th>Assists</th>
            <th>KDA</th>
            </tr>`;
            data.forEach(function(champ) {
                table += getTableRow(champ);
                console.log(getTableRow(champ));
            });
            return table;
    });
};

function getTableRow(element){
    return `<tr>
    <td>${element.winRatio}</td>
    <td>${element.champName}</td>  
    <td>${element.cs}</td>  
    <td>${element.playedMatches}</td>  
    <td>${element.avgKill}</td>  
    <td>${element.avgDeath}</td>  
    <td>${element.avgAssists}</td>  
    <td>${element.kda}</td>                  
    </tr>`;
};