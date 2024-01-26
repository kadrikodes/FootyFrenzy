//package football.frenzy.service;
//
//import football.frenzy.dataaccess.ClubRepository;
//import football.frenzy.dataaccess.DraftRepository;
//import football.frenzy.dataaccess.PlayerRepository;
//import football.frenzy.entity.ClubData;
//import football.frenzy.entity.DraftData;
//import football.frenzy.entity.PlayerData;
//import football.frenzy.entity.UserDraftSelection;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//public class DraftServicePrev {
//
//    private final DraftRepository draftRepository;
//    private String randomClub;
//
//    private HashSet<String> selectedClubs = new HashSet<>();
//    private HashSet<String> selectedPlayers = new HashSet<>();
//    private Map<String, List<String>> availablePlayers = new HashMap<>();
//
//    @Autowired
//    public DraftServicePrev(DraftRepository draftRepository) {
//        this.draftRepository = draftRepository;
//    }
//
//    @Autowired
//    private ClubRepository clubRepository;
//
//    @Autowired
//    private PlayerRepository playerRepository;
//
//    public DraftData initiateDraft() {
//        DraftData draftData = new DraftData();
//
//        // Get a random club
//        ClubData randomClub = selectRandomClub();
//        draftData.setClub(randomClub);
//
//        // Get players associated with the selected club
//        List<PlayerData> players = playerRepository.findByClub(randomClub);
//        draftData.setAvailablePlayers(players.stream().map(PlayerData::getPlayerName).collect(Collectors.toList()));
//
//        return draftData;
//    }
//
//    private ClubData selectRandomClub() {
//        // Assuming you have a method in your ClubDataRepository to get all clubs
//        List<ClubData> allClubs = clubRepository.findAll();
//
//        if (allClubs.isEmpty()) {
//            throw new RuntimeException("No clubs available.");
//        }
//
//        Random random = new Random();
//        int randomIndex = random.nextInt(allClubs.size());
//        return allClubs.get(randomIndex);
//    }
//
////    public DraftData initiateDraft() {
////        String randomClub = selectRandomClub();
////        List<String> availablePlayers = getPlayersForClub(randomClub);
////        DraftData draftData = new DraftData(randomClub, availablePlayers, new HashMap<>(), new ArrayList<>());
////        draftRepository.save(draftData);
////        return draftData;
////    }
//
//    public DraftData selectPlayer(Long draftId, String userKey, String selectedPlayer) {
//        DraftData draftData = draftRepository.findById(draftId)
//                .orElseThrow(() -> new RuntimeException("Draft not found"));
//
//        // Check if the user has already made selections
//        List<String> userSelections = draftData.getSelectedPlayersByUser()
//                .computeIfAbsent(userKey, key -> new ArrayList<>());
//
//        // Validate if the selected player is available
//        if (draftData.getAvailablePlayers().contains(selectedPlayer) && !userSelections.contains(selectedPlayer)) {
//            userSelections.add(selectedPlayer);
//            draftData.getAvailablePlayers().remove(selectedPlayer);
//
//            draftRepository.save(draftData);
//        } else {
//            // Handle invalid selection (e.g., player not available or already selected)
//            throw new RuntimeException("Invalid player selection");
//        }
//        return draftData;
//    }
//
//    public List<UserDraftSelection> getUserDraftSelections(Long userId, Long draftId) {
//    }
//
//
////    public DraftData getUpdatedDraftData(Long draftId) {
////        DraftData draftData = draftRepository.findById(draftId)
////                .orElseThrow(() -> new RuntimeException("Draft not found"));
////
////        List<String> availableClubs = getClubsNotSelected(draftData);
////
////        // Update the available clubs in the draftData
////        draftData.setAvailableClubs(availableClubs);
////
////        draftRepository.save(draftData);
////
////        return draftData;
////    }
//
////    private List<String> getClubsNotSelected(DraftData draftData) {
////        try {
////            // Retrieve the initial list of clubs from the file
////            List<String> allClubs = Files.readAllLines(Paths.get("src/main/resources/playerList"));
////
////            // Remove the clubs that have already been selected
////            List<String> selectedClubs = draftData.getSelectedClubs();
////            List<String> availableClubs = new ArrayList<>(allClubs);
////            availableClubs.removeAll(selectedClubs);
////
////            return availableClubs;
////        } catch (IOException e) {
////            // Handle the IOException (e.g., log an error)
////            throw new RuntimeException("Error reading clubs from file", e);
////        }
////    }
////
////
////    public ArrayList<PlayerData> createPlayerClubFromSQL() {
////        ArrayList<PlayerData> playerList = new ArrayList<>();
////
////        try (HikariDataSource dataSource = createDataSource();
////             Connection connection = dataSource.getConnection();
////             Statement statement = connection.createStatement()) {
////
////            createClubDataTable(statement);
////            executeSqlFile(statement);
////
////            // Process the results if any
////            if (statement.getMoreResults()) {
////                try (ResultSet resultSet = statement.getResultSet()) {
////                    while (resultSet.next()) {
////                        String club = resultSet.getString("club_name");
////                        String playerNames = resultSet.getString("player_names");
////
////                        String[] playerNameArray = playerNames.split(",");
////                        List<String> playerNameList = Arrays.asList(playerNameArray);
////
////                        ClubData clubData = new ClubData(club, new ArrayList<>());
////                        PlayerData playerClub = new PlayerData(club, new ArrayList<>(playerNameList), null, clubData);
////                        playerList.add(playerClub);
////                    }
////                }
////            }
////
////        } catch (SQLException | IOException e) {
////            throw new RuntimeException(e);
////        }
////
////        return playerList;
////    }
////
////    private HikariDataSource createDataSource() {
////        HikariConfig config = new HikariConfig();
////        config.setJdbcUrl("jdbc:h2:mem:testdb");
////        config.setUsername("sa");
////        config.setPassword("");
////        return new HikariDataSource(config);
////    }
////
////    private void createClubDataTable(Statement statement) throws SQLException {
////        // Check if the ClubData table exists
////        ResultSet resultSet = statement.getConnection().getMetaData().getTables(null, null, "ClubData", null);
////
////        if (!resultSet.next()) {
////            // Create ClubData table if it doesn't exist
////            statement.execute("CREATE TABLE ClubData (id INT PRIMARY KEY, club_name VARCHAR(255) NOT NULL)");
////
////            // Insert data into ClubData table
////            statement.execute("INSERT INTO ClubData (id, club_name) VALUES " +
////                    "(1, 'LIVERPOOL'), " +
////                    "(2, 'MANCHESTER UNITED'), " +
////                    "(3, 'ARSENAL'), " +
////                    "(4, 'TOTTENHAM HOTSPUR'), " +
////                    "(5, 'MANCHESTER CITY'), " +
////                    "(6, 'CHELSEA')");
////        }
////    }
////
////
////
////    private void executeSqlFile(Statement statement) throws IOException, SQLException {
////        Path sqlFilePath = Paths.get("src/main/resources/data.sql");
////        String sqlContent = Files.readString(sqlFilePath);
////        String[] sqlStatements = sqlContent.split(";");
////
////        for (String sqlStatement : sqlStatements) {
////            statement.execute(sqlStatement.trim());
////        }
////    }
////
////
////
////    public String selectRandomClub() {
////        ArrayList<PlayerData> playerClubList = createPlayerClubFromSQL();
////
////        if (playerClubList.isEmpty()) {
////            System.out.println("No teams available.");
////            return "";
////        }
////
////
////        Random random = new Random();
////        int randomIndex = random.nextInt(playerClubList.size());
////        PlayerData randomPlayer = playerClubList.get(randomIndex);
////
////        // Check if the club has been selected before
////        while (selectedClubs.contains(randomPlayer.getClubName())) {
////            randomIndex = random.nextInt(playerClubList.size());
////            randomPlayer = playerClubList.get(randomIndex);
////        }
////
////        selectedClubs.add(randomPlayer.getClubName());
////
////        randomClub = randomPlayer.getClubName();
////        System.out.println("Club: " + randomClub);
////        return randomClub;
////    }
////
////    public List<String> getPlayersForClub(String clubName) {
////        ArrayList<PlayerData> playerClubList = createPlayerClubFromSQL();
////        ArrayList<String> selectedPlayers = new ArrayList<>();
////
////        for (PlayerData player : playerClubList) {
////            if (player.getClubName().equals(clubName)) {
////                selectedPlayers.add(player.getPlayerName());
////            }
////        }
////
////        return selectedPlayers;
////    }
//}
