package proxet.tournament.generator;

import proxet.tournament.generator.dto.Player;
import proxet.tournament.generator.dto.TeamGeneratorResult;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TeamGenerator {

    public TeamGeneratorResult generateTeams(String filePath) {
        List<Player> firstTeam = new ArrayList<>();
        List<Player> secondTeam = new ArrayList<>();
        List<Player> res = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            res.add(new Player("1", 1));
        }
        int[] max1 = new int[]{0, 0, 0, 0, 0, 0};
        int[] max2 = new int[]{0, 0, 0, 0, 0, 0};
        int[] max3 = new int[]{0, 0, 0, 0, 0, 0};
        int[] minPos = new int[]{0, 0, 0};
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            String[] str=line.split("\\s");
            while (line != null) {

                switch (str[2]) {
                    case "1": {
                        int current = Integer.parseInt(str[1]);
                        int min = max1[0];
                        for (int j = 0; j < 6; j++) {
                            if (max1[j] <= min) {
                                min = max1[j];
                                minPos[0] = j;
                            }
                        }
                        if (current > min) {
                            max1[minPos[0]] = Integer.parseInt(str[1]);
                            res.set(minPos[0], new Player(str[0], Integer.parseInt(str[2])));
                        }
                    }
                    break;
                    case "2": {
                        int current = Integer.parseInt(str[1]);
                        int min = max2[0];
                        for (int j = 0; j < 6; j++) {
                            if (max2[j] <= min) {
                                min = max2[j];
                                minPos[1] = 6 + j;
                            }
                        }
                        if (current > min) {
                            max2[minPos[1] - 6] = Integer.parseInt(str[1]);
                            res.set(minPos[1], new Player(str[0], Integer.parseInt(str[2])));
                        }
                    }
                    break;
                    case "3": {
                        int current = Integer.parseInt(str[1]);
                        int min = max3[0];
                        for (int j = 0; j < 6; j++) {
                            if (max3[j] <= min) {
                                min = max3[j];
                                minPos[2] = 12 + j;
                            }
                        }
                        if (current > min) {
                            max3[minPos[2] - 12] = Integer.parseInt(str[1]);
                            res.set(minPos[2], new Player(str[0], Integer.parseInt(str[2])));
                        }
                    }
                    break;

                }
                // считываем остальные строки в цикле
                line = reader.readLine();
                if(line!=null)str=line.split("\\s");
            }

        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        for (int i = 0; i < 18; ) {
            firstTeam.add(res.get(i));
            i++;
            secondTeam.add(res.get(i));
            i++;
        }

        return new TeamGeneratorResult(
                firstTeam, secondTeam
        );
    }

}