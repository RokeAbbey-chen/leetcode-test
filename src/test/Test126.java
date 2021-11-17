package test;

import javax.swing.*;
import java.util.*;

public class Test126 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        char[][] words = new char[wordList.size() + 1][];
        int i = 0;
        int endIndex = -1;
        for (String word: wordList) {
            if (endWord.equals(word)) endIndex = i;
            words[i] = word.toCharArray();
            i ++;
        }
        if (-1 == endIndex) return new ArrayList<>(0);
        words[words.length - 1] = beginWord.toCharArray();
        return search(endIndex, words, wordList);
    }

    public List<List<String>> search(int endIndex, char[][] words, List<String> wordsStr) {

        HashSet<Integer>[] bestPredecessor = new HashSet [words.length];
        int[][] bestLevels = new int[2][words.length];

        int inf = Integer.MAX_VALUE - 10;
        for (int i = 0; i < words.length; i ++) {
            bestPredecessor[i] = new HashSet<>();
            bestLevels[0][i] = inf;
            bestLevels[1][i] = inf;
        }
        bestLevels[0][words.length - 1] = 1;
        bestLevels[1][endIndex] = 1;
        int [] levels = {1, 1};

        LinkedList<Integer>[] queues = new LinkedList[]{new LinkedList(), new LinkedList()};
        queues[0].add(words.length - 1);
        queues[1].add(endIndex);
        ArrayList<List<String>> result = new ArrayList<>();

        while (!queues[0].isEmpty() && !queues[1].isEmpty()) {
            int which = 0;
            if (queues[0].size() > queues[1].size()) which = 1;

            LinkedList<Integer> sharedQueue = new LinkedList<>();
            HashSet<Integer> repeat = new HashSet<>();
            boolean shouldFinish = false;
            while (!queues[which].isEmpty()) {
                int index = queues[which].removeFirst();
                HashSet<Integer> successors = getSuccessors(index, words);
                for (Integer suc: successors) {
                    if (bestLevels[(which + 1) & 1][suc] < inf) {
                        /** 在对面找到后继，直接加入答案 */
                        shouldFinish = true;
                        List<LinkedList<Integer>> paths = concat(index, suc, bestPredecessor, which);
                        result.addAll(transInt2Str(paths, words));
                    } else if (bestLevels[which][suc] < levels[which]) {
                        /** 发现环，continue*/
                        continue;
                    } else if (!shouldFinish){
                        /** 如果连通 就不需要进行这些工作，准备返回就行了*/
                        bestLevels[which][suc] = levels[which];
                        bestPredecessor[suc].add(index);
                        if (!repeat.contains(suc)) sharedQueue.addLast(suc);
                        repeat.add(suc);
                    }

                }
            }
            if (shouldFinish) return result;

            levels[which] ++;
            queues[which] = sharedQueue;
            repeat.clear();

        }
        return result;
    }


    public List<LinkedList<Integer>> concat(int index0, int index1, HashSet<Integer>[] predecessor, int reverse) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();
        LinkedList<LinkedList<Integer>> list = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        if (0 == reverse) {path.add(index0); path.add(index1);}
        if (1 == reverse) {path.add(index1); path.add(index0);}
        list.add(path);

        boolean havePred;
        do {
            havePred = false;
            LinkedList<LinkedList<Integer>> tmpList = new LinkedList<>() ;
            for (LinkedList<Integer> intPath: list) {
                HashSet<Integer> pred = predecessor[reverse == 0 ?intPath.getFirst(): intPath.getLast()];
                if (pred.isEmpty()) break;
                havePred = true;
                LinkedList<LinkedList<Integer>> tmp =  unfold(pred, intPath, reverse);
                tmpList.addAll(tmp);
            }
            if (havePred) list = tmpList;
        } while (havePred);

        do {
            havePred = false;
            LinkedList<LinkedList<Integer>> tmpList = new LinkedList<>() ;
            for (LinkedList<Integer> intPath: list) {
                HashSet<Integer> pred = predecessor[reverse == 1 ?intPath.getFirst(): intPath.getLast()];
                if (pred.isEmpty()) break;
                havePred = true;
                LinkedList<LinkedList<Integer>> tmp =  unfold(pred, intPath, 1 - reverse);
                tmpList.addAll(tmp);
            }
            if (havePred) list = tmpList;
        } while (havePred);
        result.addAll(list);
        return result;
    }

    public LinkedList<LinkedList<Integer>> unfold(HashSet<Integer> set, LinkedList<Integer> intPath, int reverse) {
        LinkedList<LinkedList<Integer>> result = new LinkedList<>();
        for (Integer i : set) {
            LinkedList<Integer> tmp = (LinkedList<Integer>) intPath.clone();
            if (reverse == 0) tmp.addFirst(i);
            else tmp.addLast(i);
            result.add(tmp);
        }
        return result;
    }


    public HashSet<Integer> getSuccessors(int index, char[][] words) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < words.length; i ++) {
            if (i == index) continue;
            if (1 == differentCount(words[index], words[i])) set.add(i);
        }
        return set;
    }

    public int differentCount(char[] word0, char[] word1) {
        int count = 0;
        for (int i = 0; i < word0.length; i ++) if (word0[i] != word1[i]) count ++;
        return count;
    }

    public List<List<String>> transInt2Str(List<LinkedList<Integer>> intPaths, char[][] words) {
        ArrayList<List<String>> result = new ArrayList<>();
        for (LinkedList<Integer> path: intPaths) {
            ArrayList<String> sPath = new ArrayList<>();
            for (int i : path) {
                sPath.add(new String(words[i]));
            }
            result.add(sPath);
        }
        return result;
    }

    public <T> boolean hasRepeat(List<T> list) {
        HashSet<T> set = new HashSet<>();
        set.addAll(list);
        return set.size() != list.size();
    }

    public static void main(String[] args) {
        Test126 t = new Test126();
//        String begin = "hel";
//        String end = "obd";
//        List<String> words = Arrays.asList("hed", "hbl", "hbd", "obd");

//        String begin = "cet";
//        String end = "ism";
//        List<String> words = Arrays.asList("kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob");

//        String begin = "hit";
//        String end = "cog";
//        List<String> words = Arrays.asList("hot","dot","dog","lot","log","cog");
        /**
         * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
         * "hit" -> "hot" -> "lot" -> "log" -> "cog"
         */

//        String begin = "magic";
//        String end = "pearl";
//        List<String> words = Arrays.asList("flail","halon","lexus","joint","pears","slabs","lorie","lapse","wroth","yalow","swear","cavil","piety","yogis","dhaka","laxer","tatum","provo","truss","tends","deana","dried","hutch","basho","flyby","miler","fries","floes","lingo","wider","scary","marks","perry","igloo","melts","lanny","satan","foamy","perks","denim","plugs","cloak","cyril","women","issue","rocky","marry","trash","merry","topic","hicks","dicky","prado","casio","lapel","diane","serer","paige","parry","elope","balds","dated","copra","earth","marty","slake","balms","daryl","loves","civet","sweat","daley","touch","maria","dacca","muggy","chore","felix","ogled","acids","terse","cults","darla","snubs","boats","recta","cohan","purse","joist","grosz","sheri","steam","manic","luisa","gluts","spits","boxer","abner","cooke","scowl","kenya","hasps","roger","edwin","black","terns","folks","demur","dingo","party","brian","numbs","forgo","gunny","waled","bucks","titan","ruffs","pizza","ravel","poole","suits","stoic","segre","white","lemur","belts","scums","parks","gusts","ozark","umped","heard","lorna","emile","orbit","onset","cruet","amiss","fumed","gelds","italy","rakes","loxed","kilts","mania","tombs","gaped","merge","molar","smith","tangs","misty","wefts","yawns","smile","scuff","width","paris","coded","sodom","shits","benny","pudgy","mayer","peary","curve","tulsa","ramos","thick","dogie","gourd","strop","ahmad","clove","tract","calyx","maris","wants","lipid","pearl","maybe","banjo","south","blend","diana","lanai","waged","shari","magic","duchy","decca","wried","maine","nutty","turns","satyr","holds","finks","twits","peaks","teems","peace","melon","czars","robby","tabby","shove","minty","marta","dregs","lacks","casts","aruba","stall","nurse","jewry","knuth");

        String begin = "nape";
        String end = "mild";
        List<String> words = Arrays.asList("dose","ends","dine","jars","prow","soap","guns","hops","cray","hove","ella","hour","lens","jive","wiry","earl","mara","part","flue","putt","rory","bull","york","ruts","lily","vamp","bask","peer","boat","dens","lyre","jets","wide","rile","boos","down","path","onyx","mows","toke","soto","dork","nape","mans","loin","jots","male","sits","minn","sale","pets","hugo","woke","suds","rugs","vole","warp","mite","pews","lips","pals","nigh","sulk","vice","clod","iowa","gibe","shad","carl","huns","coot","sera","mils","rose","orly","ford","void","time","eloy","risk","veep","reps","dolt","hens","tray","melt","rung","rich","saga","lust","yews","rode","many","cods","rape","last","tile","nosy","take","nope","toni","bank","jock","jody","diss","nips","bake","lima","wore","kins","cult","hart","wuss","tale","sing","lake","bogy","wigs","kari","magi","bass","pent","tost","fops","bags","duns","will","tart","drug","gale","mold","disk","spay","hows","naps","puss","gina","kara","zorn","boll","cams","boas","rave","sets","lego","hays","judy","chap","live","bahs","ohio","nibs","cuts","pups","data","kate","rump","hews","mary","stow","fang","bolt","rues","mesh","mice","rise","rant","dune","jell","laws","jove","bode","sung","nils","vila","mode","hued","cell","fies","swat","wags","nate","wist","honk","goth","told","oise","wail","tels","sore","hunk","mate","luke","tore","bond","bast","vows","ripe","fond","benz","firs","zeds","wary","baas","wins","pair","tags","cost","woes","buns","lend","bops","code","eddy","siva","oops","toed","bale","hutu","jolt","rife","darn","tape","bold","cope","cake","wisp","vats","wave","hems","bill","cord","pert","type","kroc","ucla","albs","yoko","silt","pock","drub","puny","fads","mull","pray","mole","talc","east","slay","jamb","mill","dung","jack","lynx","nome","leos","lade","sana","tike","cali","toge","pled","mile","mass","leon","sloe","lube","kans","cory","burs","race","toss","mild","tops","maze","city","sadr","bays","poet","volt","laze","gold","zuni","shea","gags","fist","ping","pope","cora","yaks","cosy","foci","plan","colo","hume","yowl","craw","pied","toga","lobs","love","lode","duds","bled","juts","gabs","fink","rock","pant","wipe","pele","suez","nina","ring","okra","warm","lyle","gape","bead","lead","jane","oink","ware","zibo","inns","mope","hang","made","fobs","gamy","fort","peak","gill","dino","dina","tier");
        System.out.println(t.findLadders(begin, end, words));
    }
}
