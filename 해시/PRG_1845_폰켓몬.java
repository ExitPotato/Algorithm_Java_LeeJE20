/*
처음엔 hash로 했는데 set으로 해도 잘 됨

*/

import java.util.*;

class Solution {
    public int solution(int[] nums) {
        // Map<Integer, Integer> pokemons = new HashMap<>();
        // for (int n: nums) {
        //     Integer current = pokemons.getOrDefault(n, 0);
        //     pokemons.put(n, current + 1);
        // }
        
        
        Set<Integer> pokemonSet = new HashSet<>();
        for (int n: nums) {
            pokemonSet.add(n);
        }
        
        int size = pokemonSet.size();
        
        int target = nums.length/2;
        
        int answer =  size >= target ? target: size;
        
        // int answer = pokemons.size() >= target ? target: pokemons.size();
        return answer;
    }
}
