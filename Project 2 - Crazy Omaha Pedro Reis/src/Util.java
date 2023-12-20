import java.util.ArrayList;
import java.util.List;

public class Util {

    // get all combinations given a list of values, and the number of items to choose out of the values
    public static <T> List<List<T>> getAllPossibleCombinations(List<T> values, int numberOfItemsToSelect) {
        List<List<T>> result = new ArrayList<>();
        getAllPossibleCombinationsHelper(values, numberOfItemsToSelect, 0, new ArrayList<>(), result);
        return result;
    }

    // recursive helper method to generate
    private static <T> void getAllPossibleCombinationsHelper(List<T> values, int numberOfItemsRemainingToSelect, int start, List<T> currentSelection, List<List<T>> result) {
        if (0 == numberOfItemsRemainingToSelect) {
            result.add(new ArrayList<>(currentSelection));
            return;
        }

        for (int i = start; i < values.size(); i++) {
            currentSelection.add(values.get(i));
            getAllPossibleCombinationsHelper(values, numberOfItemsRemainingToSelect - 1, i + 1, currentSelection, result);
            currentSelection.remove(currentSelection.size() - 1);
        }
    }
}
