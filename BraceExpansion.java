//time  2 pow MN, m i avg length, N no of blocks.
//space o(MN)
class Solution {
    List<List<Character>> blocks;
    List<String> res;
    
    public String[] expand(String S) {
        if(S == null || S.length() == 0)
            return new String[0];
        
        res = new ArrayList<>();
        blocks = new ArrayList<>();
        
        //process the string
        for(int i=0; i<S.length(); i++) {
            List<Character> block = new ArrayList<>();
            if(S.charAt(i) == '{') {
                i++;
                while(S.charAt(i) != '}') {
                    if(S.charAt(i) == ',') {
                        i++;
                        continue;
                    }
                    else {
                        block.add(S.charAt(i));
                    }
                    i++;
                }
            }
            else {
                block.add(S.charAt(i));
            }
            blocks.add(block);
        }
        
        backtrack(0, new StringBuilder());
        
        String[] resultArr = res.toArray(new String[res.size()]);
        Arrays.sort(resultArr);
        return resultArr;
    }
    
    private void backtrack(int index, StringBuilder sb) {
        //base case
        if(index == blocks.size()) {
            res.add(sb.toString());
            return;
        }
        //logic
        for(char ch: blocks.get(index)) {
            //action
            sb.append(ch);
            //recurse
            backtrack(index + 1, sb);
            //backtrack
            sb.deleteCharAt(sb.length()-1);
        }
    }
}