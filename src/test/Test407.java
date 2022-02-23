package test;

public class Test407 {
    public int trapRainWater(int[][] heightMap) {
        int[][] totalAlt = new int[heightMap.length][heightMap[0].length];
        solve(heightMap, totalAlt);
        int waterSum = 0;
        for (int i = 0; i < heightMap.length; i ++) {
            for (int j = 0; j < heightMap[i].length; j ++) {
                waterSum += totalAlt[i][j] - heightMap[i][j];
            }
        }
        return waterSum;
    }

    public void solve(int[][] heightMap, int[][] totalAlt) {
        for (int i = 0; i < heightMap.length; i ++) {
            totalAlt[i] = rainRow(heightMap[i]);
        }
        leakCol(heightMap, totalAlt, 0);
    }

    public int[] rainRow(int[] heightRow) {
        int len = heightRow.length;
        int[] totalAltRow = new int[len];
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        leftMax[0] = heightRow[0];
        rightMax[len - 1] = heightRow[len - 1];
        for (int i = 1; i < len; i ++) {
            leftMax[i] = Math.max(leftMax[i - 1], heightRow[i]);
            rightMax[len - i - 1] = Math.max(rightMax[len - i], heightRow[len - i - 1]);
        }

        for (int i = 0; i < len; i ++) {
            totalAltRow[i] = Math.min(leftMax[i], rightMax[i]);
        }
        return totalAltRow;
    }

    public void leakCol(int[][] heightMap, int[][] totalAlt, int level) {
        int len0, len1;
        len0 = heightMap.length;
        len1 = heightMap[0].length;

        for (int colI = 0; colI < len1; colI ++) {
            totalAlt[0][colI] = heightMap[0][colI];
            totalAlt[len0  - 1][colI] = heightMap[len0 - 1][colI];
        }

        boolean noChange = true;
        for (int rowI = 1; rowI < len0; rowI ++) {
            int rowI1 = len0 - 1 - rowI;
            for (int colI = 0; colI < len1; colI ++) {
                int tmp = totalAlt[rowI][colI];
                totalAlt[rowI][colI] = Math.min(Math.max(totalAlt[rowI - 1][colI], heightMap[rowI][colI]), totalAlt[rowI][colI]);
                noChange = noChange && totalAlt[rowI][colI] == tmp;

                tmp = totalAlt[rowI1][colI];
                totalAlt[rowI1][colI] = Math.min(Math.max(totalAlt[rowI1 + 1][colI], heightMap[rowI1][colI]), totalAlt[rowI1][colI]);
                noChange = noChange && totalAlt[rowI1][colI] == tmp;
            }
        }
        leakRow(heightMap, totalAlt, level);
    }

    public void leakRow(int[][] heightMap, int[][] totalAlt, int level) {
        int len0, len1;
        len0 = heightMap[0].length;
        len1 = heightMap.length;

        for (int colI = 0; colI < len1; colI ++) {
            totalAlt[colI][0] = heightMap[colI][0];
            totalAlt[colI][len0 - 1] = heightMap[colI][len0 - 1];
        }

        boolean noChange = true;
        for (int rowI = 1; rowI < len0; rowI ++) {
            int rowI1 = len0 - 1 - rowI;
            for (int colI = 0; colI < len1; colI ++) {
                int tmp = totalAlt[colI][rowI];
                totalAlt[colI][rowI] = Math.min(Math.max(totalAlt[colI][rowI - 1], heightMap[colI][rowI]), totalAlt[colI][rowI]);
                noChange = noChange && totalAlt[colI][rowI] == tmp;

                tmp = totalAlt[colI][rowI1];
                totalAlt[colI][rowI1] = Math.min(Math.max(totalAlt[colI][rowI1 + 1], heightMap[colI][rowI1]), totalAlt[colI][rowI1]);
                noChange = noChange && totalAlt[colI][rowI1] == tmp;
            }
        }
        if (noChange && level <= 0)
            leakCol(heightMap, totalAlt, level + 1);
        if (!noChange)
            leakCol(heightMap, totalAlt, level);
    }

    public static void print(int[][] mat) {
        for (int i = 0; i < mat.length; i ++) {
            for (int j = 0; j < mat[i].length; j ++) {
                System.out.printf("%4d ", mat[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Test407 t = new Test407();
        int[][] heightMap = {
                {5, 5, 5, 5, 5, 5, 5},
                {3, 3, 3, 3, 3, 2, 5},
                {3, 2, 2, 2, 3, 3, 3},
                {3, 2, 1, 2, 2, 1, 4},
                {3, 2, 2, 2, 3, 1, 1},
                {4, 4, 3, 3, 3, 5, 7},
                {3, 1, 3, 3, 1, 1, 7},
                {3, 1, 1, 1, 1, 5, 7},
                {3, 5, 5, 6, 3, 5, 7},
        };

        heightMap = new int[][]{
                {5, 4, 4, 5, 3, 7, 5},
                {7, 2, 2, 2, 2, 2, 7},
                {3, 1, 1, 1, 3, 3, 6},
                {3, 2, 1, 7, 2, 7, 4},
                {3, 2, 2, 2, 3, 1, 1},
                {4, 4, 4, 4, 4, 5, 7},
        };
        heightMap = new int[][]{
                {3, 3, 3, 3},
                {3, 1, 1, 3},
                {3, 3, 3, 3}
        };

//        heightMap = new int[][]{{19383,10886,12777,16915,17793,18335,15386,10492,16649,11421},{12362,27,8690,59,7763,3926,540,3426,9172,5736},{15211,5368,2567,6429,5782,1530,2862,5123,4067,3135},{13929,9802,4022,3058,3069,8167,1393,8456,5011,8042},{16229,7373,4421,4919,3784,8537,5198,4324,8315,4370},{16413,3526,6091,8980,9956,1873,6862,9170,6996,7281},{12305,925,7084,6327,336,6505,846,1729,1313,5857},{16124,3895,9582,545,8814,3367,5434,364,4043,3750},{11087,6808,7276,7178,5788,3584,5403,2651,2754,2399},{19932,5060,9676,3368,7739,12,6226,8586,8094,7539}};
//        heightMap = new int[][]{{1103,1106,1107,1105,1103,1105,1106,1102,1109,1101,1102,1107,1100,1109,1103,1106,1100,1106,1102,1106,1101,1108,1107,1109,1102,1100,1102,1103,1107,1105,1109,1102,1102,1108,1109,1107,1103,1106,1101,1102,1109,1103,1101,1109,1104,1107,1108,1104,1105,1100},{1103,536,101,990,966,883,872,180,1006,291,315,935,94,337,346,515,856,739,323,867,134,905,592,555,824,377,444,374,53,760,97,818,286,188,798,594,413,661,764,409,942,70,686,378,749,22,236,596,104,549},{1105,580,444,388,477,611,107,912,327,502,662,766,311,290,296,451,875,699,454,629,450,739,41,127,107,781,491,685,719,937,577,866,507,363,596,975,316,693,229,634,538,881,742,839,513,29,280,378,718,725},{1100,159,806,733,628,255,856,461,931,565,389,498,774,238,851,360,203,510,44,774,134,924,997,866,753,501,237,375,869,946,442,561,447,238,285,417,484,131,868,405,39,247,245,803,828,438,153,21,938,539},{1106,414,453,773,623,548,616,850,914,828,138,698,379,927,927,1006,334,753,480,193,500,509,782,735,654,600,515,149,964,796,679,92,552,474,207,517,365,814,358,621,632,838,309,353,756,578,350,432,321,820},{1105,811,671,740,888,315,330,746,454,636,532,475,718,426,292,268,934,647,72,634,610,46,462,909,389,560,478,81,983,141,891,940,943,904,670,173,209,991,909,1006,969,783,823,678,200,105,936,476,94,350},{1100,694,386,552,946,117,455,766,189,428,897,422,358,182,669,19,346,220,352,597,216,311,723,382,331,265,829,609,731,914,949,821,950,677,715,238,137,160,994,668,930,234,432,279,406,91,640,94,302,982},{1102,860,635,395,232,309,650,52,908,723,308,200,534,600,219,591,829,346,742,165,1004,14,389,779,283,786,860,265,870,152,589,894,1003,215,631,577,514,623,971,764,336,269,954,212,212,516,794,31,852,878},{1108,199,882,918,968,508,46,818,763,258,313,343,143,658,900,764,577,756,378,539,510,56,798,807,259,1000,313,43,373,507,263,902,696,135,162,1006,985,198,167,739,446,470,424,931,470,314,38,37,60,758},{1106,912,804,707,709,53,49,12,438,413,510,691,657,548,169,161,545,144,349,702,225,137,514,639,59,974,295,439,353,345,187,910,248,981,959,299,377,998,302,805,753,154,839,400,692,350,551,579,836,242},{1101,52,370,127,33,771,91,319,200,435,1006,377,687,244,700,636,534,67,624,178,215,368,322,396,110,356,736,1004,926,562,588,539,956,300,657,980,61,90,641,603,867,637,322,896,224,365,522,100,422,489},{1100,979,199,284,365,651,630,443,997,898,348,576,780,294,866,427,616,270,859,247,215,69,227,528,955,793,883,468,883,647,299,493,617,488,767,324,481,739,110,469,628,448,35,398,84,243,167,691,503,368},{1100,709,427,849,579,373,632,804,183,857,441,472,692,400,302,801,67,125,531,167,584,501,957,961,241,31,547,750,64,40,108,335,91,526,526,12,241,149,806,414,348,590,228,31,980,872,822,389,987,695},{1106,914,186,493,217,769,867,754,509,921,137,960,246,570,828,115,573,59,254,721,815,944,301,385,965,624,599,778,1003,928,815,892,832,992,727,40,103,584,136,603,496,263,553,84,824,723,189,387,772,785},{1108,929,720,742,304,27,356,245,147,701,163,953,583,338,935,301,720,28,227,846,973,65,100,868,140,914,581,671,643,695,799,83,614,861,815,260,878,513,495,16,205,649,959,130,977,236,773,687,606,991},{1105,570,46,965,780,528,221,352,542,206,389,331,280,994,182,437,244,50,293,82,408,840,73,357,960,40,583,724,69,532,57,934,92,445,242,214,964,453,908,496,650,288,169,272,272,693,51,858,733,334},{1102,132,164,345,831,467,375,757,181,786,279,228,711,713,663,943,917,969,738,816,807,730,94,318,344,708,1001,386,908,725,62,181,199,569,516,20,26,234,119,549,10,388,119,63,91,124,348,999,436,77},{1107,233,797,241,542,132,291,885,860,189,600,264,360,141,823,867,504,191,91,613,730,443,992,191,497,425,306,835,414,732,902,561,307,42,144,191,516,425,67,718,605,1009,972,307,493,786,164,987,319,597},{1102,392,31,276,573,870,692,221,695,96,295,940,1000,593,324,486,126,830,902,535,538,849,535,500,146,370,628,653,347,938,592,631,320,965,898,235,825,580,447,863,18,732,793,360,667,107,837,136,279,81},{1101,159,920,538,649,408,898,620,403,587,900,986,209,562,941,97,787,109,667,576,962,27,651,745,378,308,194,205,786,815,276,438,964,538,318,603,288,207,565,682,784,455,10,335,1007,293,422,137,392,431},{1103,344,449,344,431,169,995,967,364,771,772,982,551,726,862,860,672,492,409,227,164,183,25,516,861,374,800,273,501,182,47,547,869,838,881,290,997,866,600,351,980,362,675,521,79,527,371,93,361,122},{1100,516,648,677,374,499,42,164,114,885,689,151,422,548,979,646,180,966,854,770,659,824,475,324,336,896,193,49,979,545,162,631,403,800,299,119,641,683,274,745,558,305,887,323,843,208,959,365,165,803},{1108,166,970,943,833,296,181,368,687,150,255,191,771,1000,333,60,110,964,85,374,52,634,669,929,299,854,479,248,561,986,393,29,143,353,314,966,991,485,676,21,977,922,202,739,912,878,141,12,184,217},{1108,226,193,387,497,482,583,967,72,135,943,807,506,428,151,163,736,484,990,403,495,958,315,40,39,569,908,170,572,434,729,290,651,912,20,490,736,593,799,150,718,733,948,567,503,441,720,230,915,700},{1103,401,648,280,431,677,839,681,190,753,105,909,34,98,164,396,579,242,979,720,383,40,443,673,597,289,104,659,509,361,349,474,752,340,96,525,359,925,196,891,21,644,143,397,732,297,783,653,529,752},{1104,254,134,149,269,73,428,363,722,279,715,414,743,809,744,829,325,445,97,863,679,460,497,812,847,572,99,620,215,970,714,921,567,839,413,826,902,831,532,615,453,589,371,538,388,457,710,55,892,797},{1109,561,599,396,363,436,958,804,46,516,117,102,427,674,931,830,490,176,1004,364,133,447,943,494,327,322,941,27,719,175,166,618,79,755,1005,432,181,305,579,569,811,686,662,581,350,935,753,182,101,99},{1107,576,888,822,60,206,134,343,223,196,509,380,804,578,125,151,352,649,447,273,208,600,949,212,523,641,138,267,814,581,356,693,148,235,505,550,431,982,236,644,168,735,366,962,655,482,456,349,121,893},{1103,671,835,552,226,349,184,354,606,340,277,304,23,767,529,870,660,302,842,886,289,1000,963,645,305,608,117,751,947,580,986,550,594,811,93,810,502,619,506,450,949,773,745,314,883,616,174,533,261,359},{1101,540,349,714,175,996,312,635,89,601,557,417,494,141,571,929,941,63,538,437,504,829,553,591,133,778,197,649,653,448,998,404,330,690,108,496,28,762,473,108,705,20,515,189,152,76,108,435,482,988},{1103,976,807,758,557,282,526,96,922,169,887,910,563,207,942,13,45,961,117,508,59,164,871,916,344,13,335,794,438,807,773,643,125,570,391,24,195,907,110,107,418,339,359,323,889,644,326,924,595,785},{1105,996,940,636,902,626,639,579,762,419,376,525,405,843,438,786,857,623,36,310,72,796,639,773,110,518,407,426,785,992,554,550,330,836,528,575,804,509,144,556,918,863,72,313,696,852,442,544,817,820},{1104,879,606,825,994,706,334,392,475,461,726,371,353,47,197,871,612,991,370,98,889,630,951,303,934,638,145,718,172,952,880,1006,173,476,821,510,525,497,244,342,300,960,703,643,349,890,504,303,223,864},{1102,454,485,333,748,761,961,883,821,475,178,691,823,693,509,987,545,24,474,779,356,117,82,401,750,421,633,597,67,846,803,449,291,630,124,381,381,428,606,544,893,774,577,707,810,77,684,345,443,500},{1107,142,959,539,533,700,302,157,639,359,345,432,150,978,53,265,349,776,35,946,663,270,62,230,967,214,297,993,550,731,836,1007,215,137,888,738,179,180,237,808,530,573,231,670,893,626,277,233,392,302},{1101,45,563,573,618,872,778,905,208,670,978,386,19,183,513,897,264,683,67,491,833,939,406,54,952,290,22,219,865,757,864,376,144,769,291,752,983,411,648,181,423,968,909,432,494,765,671,100,790,81},{1103,613,10,330,10,952,962,22,514,817,769,368,535,904,127,168,646,100,570,636,624,983,947,875,758,431,630,419,873,410,842,796,14,843,468,366,137,420,378,641,579,138,351,456,384,468,615,20,911,175},{1109,877,500,936,742,248,709,715,10,572,467,842,358,471,27,817,179,507,579,548,138,149,28,480,595,402,290,552,764,543,717,753,410,560,31,495,798,730,200,150,644,657,335,993,471,704,152,640,201,73},{1100,330,564,548,152,502,940,432,44,695,318,104,790,718,654,812,555,794,532,97,935,167,745,612,502,558,306,996,540,850,59,61,522,966,599,664,458,882,438,492,567,98,586,347,807,230,149,704,15,24},{1102,292,533,879,246,25,427,894,363,309,734,764,360,246,720,302,252,168,174,33,651,731,121,579,420,270,800,912,965,157,926,99,791,449,968,27,816,385,911,521,684,988,275,387,576,986,679,171,144,843},{1106,137,916,1009,707,326,270,849,580,577,996,496,18,777,287,976,146,445,703,47,956,729,377,222,106,944,550,127,105,684,960,641,812,218,640,861,535,252,700,457,171,686,944,179,805,573,145,941,361,190},{1100,307,910,698,871,1006,984,411,124,79,438,426,62,592,635,692,443,512,287,133,959,800,161,245,970,956,809,457,239,512,638,559,809,538,599,23,886,573,776,1000,994,204,769,46,786,394,81,219,248,710},{1104,549,500,845,785,460,791,936,260,372,438,888,274,589,768,863,954,644,779,721,987,115,267,746,152,44,482,575,605,720,275,642,259,117,477,386,568,611,312,170,973,92,48,237,24,806,443,968,440,564},{1109,417,669,937,505,811,323,977,728,270,39,345,902,641,453,722,17,363,323,672,523,638,106,561,866,120,709,651,79,491,205,100,899,864,379,746,18,692,714,736,305,743,424,197,374,867,261,734,220,574},{1108,733,203,844,636,411,955,335,404,376,816,599,466,57,805,836,794,813,870,850,892,165,583,658,705,300,515,956,376,77,873,114,800,418,300,778,171,245,103,565,611,261,154,420,661,301,598,445,457,458},{1105,691,966,210,339,661,852,844,959,570,911,174,674,53,582,965,821,743,552,266,650,506,869,146,268,520,438,856,307,885,304,934,566,260,135,895,263,329,81,565,890,334,729,906,377,654,213,540,739,756},{1106,380,604,655,868,862,518,296,708,815,523,354,740,431,957,217,668,210,888,739,117,768,63,189,17,782,185,220,312,914,318,450,636,912,96,495,116,956,133,814,761,647,511,843,420,458,402,79,10,281},{1100,118,391,566,297,398,338,472,961,993,728,269,433,355,524,871,192,982,817,667,139,921,304,640,754,67,88,147,136,88,770,638,196,151,194,835,892,875,649,843,858,368,454,633,65,320,495,599,293,654},{1106,422,565,903,52,310,960,130,799,438,560,559,66,747,52,251,924,934,468,564,119,668,274,564,291,329,226,128,270,509,773,516,273,328,409,315,980,711,787,121,139,338,22,196,427,65,789,693,989,599},{1107,99,257,863,1005,890,534,221,1009,794,721,124,653,336,794,52,642,117,106,771,228,235,451,241,773,220,296,904,904,627,845,493,68,92,347,63,325,223,627,324,1008,690,790,651,16,574,45,648,33,141}};
//        heightMap = new int[][]{{19383,10886,12777,16915,17793,18335,15386,10492,16649,11421},{12362,27,8690,59,7763,3926,540,3426,9172,5736},{15211,5368,2567,6429,5782,1530,2862,5123,4067,3135},{13929,9802,4022,3058,3069,8167,1393,8456,5011,8042},{16229,7373,4421,4919,3784,8537,5198,4324,8315,4370},{16413,3526,6091,8980,9956,1873,6862,9170,6996,7281},{12305,925,7084,6327,336,6505,846,1729,1313,5857},{16124,3895,9582,545,8814,3367,5434,364,4043,3750},{11087,6808,7276,7178,5788,3584,5403,2651,2754,2399},{19932,5060,9676,3368,7739,12,6226,8586,8094,7539}};
        heightMap = new int[][]{{1107,1100,1106,1107,1102,1102,1106,1109,1106,1102,1106,1102,1103,1109,1107,1105,1105,1107,1104,1107,1101,1105,1102,1103,1108,1107,1102,1101,1109,1109,1103,1107,1101,1100,1106,1106,1104,1104,1105,1100,1106,1103,1105,1100,1104,1102,1107,1109,1101,1104},{1108,352,229,970,405,497,739,960,138,801,821,914,400,445,94,666,381,688,421,898,481,429,371,26,291,195,470,399,276,721,453,744,63,24,56,810,511,785,760,991,928,572,895,318,359,979,327,730,10,738},{1100,481,509,331,849,791,868,309,532,134,20,327,868,73,341,914,873,194,41,976,175,960,890,413,268,239,734,585,959,734,665,271,557,517,592,396,298,802,695,172,926,705,841,136,120,524,40,984,708,71},{1102,225,21,182,628,632,411,705,559,712,429,215,973,977,74,907,363,714,699,401,876,615,448,59,741,559,573,123,885,623,536,177,838,900,349,809,522,102,856,423,804,275,980,119,594,44,16,948,748,705},{1101,966,662,129,15,745,678,578,210,905,543,736,424,724,978,763,875,490,207,721,256,1001,986,226,110,571,261,468,861,351,515,894,308,519,13,313,254,34,234,806,929,767,885,344,833,853,449,698,686,647},{1109,932,990,738,500,443,651,751,901,502,445,407,386,743,916,741,398,513,765,622,309,37,732,536,723,555,380,162,244,56,151,995,330,132,723,820,565,364,914,808,856,349,205,584,82,464,316,470,967,423},{1105,618,450,509,145,163,54,867,668,640,913,809,626,233,283,339,395,190,694,299,989,892,638,536,819,62,990,477,875,299,890,300,260,683,799,747,188,195,604,846,826,859,998,442,434,623,771,819,156,807},{1109,135,42,89,13,851,142,994,318,1007,635,550,649,885,575,790,622,754,975,568,942,791,417,282,575,841,896,689,1003,42,486,102,519,518,181,874,711,665,210,19,14,836,912,653,63,477,433,28,573,751},{1106,506,532,994,130,450,177,16,129,170,400,957,614,261,818,138,126,519,793,326,881,798,504,783,793,558,602,569,576,166,310,152,14,184,488,134,624,1008,493,743,168,235,43,773,487,851,253,955,360,36},{1103,231,176,118,356,312,18,301,871,584,457,171,78,813,345,909,937,960,907,420,45,417,998,78,532,475,271,775,772,622,154,385,195,672,845,894,974,853,185,835,779,984,996,200,787,684,99,714,986,348},{1107,373,755,465,794,630,282,55,395,396,667,891,123,205,554,959,89,518,154,264,696,276,590,34,466,367,708,555,423,36,245,242,400,990,49,184,962,673,229,348,59,239,581,173,786,125,474,865,986,970},{1101,672,236,51,48,692,760,99,589,173,125,824,406,867,157,445,41,109,109,613,799,510,194,371,25,322,838,841,177,166,802,990,180,28,383,219,63,133,308,994,296,423,809,44,633,308,832,16,759,283},{1109,901,783,155,614,151,467,442,982,986,951,774,318,121,145,691,330,550,814,628,534,453,394,685,839,17,983,661,23,85,286,985,976,412,482,932,553,292,716,525,620,657,642,929,769,129,962,89,669,119},{1100,193,914,444,869,743,803,194,747,168,621,23,495,939,425,968,861,968,602,568,836,212,215,468,483,974,587,436,406,598,545,456,781,801,242,992,534,35,529,271,545,140,637,31,422,52,341,273,363,933},{1101,189,487,389,999,961,353,928,739,749,516,626,547,639,417,779,622,293,156,493,907,692,975,534,65,387,928,748,651,281,671,824,812,500,203,153,803,899,71,532,990,577,500,528,207,907,649,171,543,796},{1104,440,830,971,964,237,349,882,975,342,506,988,156,660,830,350,804,624,591,217,498,571,785,989,441,334,238,81,847,771,219,491,553,391,452,507,618,143,732,935,475,228,913,622,230,85,314,24,699,247},{1102,540,808,359,519,240,35,99,311,872,213,872,705,756,253,499,606,861,633,328,786,450,898,41,414,470,116,70,837,158,307,411,688,106,760,549,688,137,990,341,999,193,203,46,292,446,535,240,297,510},{1108,425,951,798,808,707,258,266,768,85,766,65,838,444,513,588,335,191,67,316,522,408,851,715,444,133,151,322,363,790,174,263,557,467,403,707,165,1004,316,923,431,72,330,260,859,834,190,184,15,248},{1100,528,646,332,585,433,807,727,745,513,859,909,118,759,367,512,456,522,506,762,787,279,825,107,529,674,931,710,200,289,948,681,807,936,355,734,359,152,803,94,1007,653,346,116,402,703,618,200,567,466},{1103,344,735,120,441,607,136,715,307,326,994,597,349,143,875,694,867,577,189,661,661,186,656,349,644,48,394,604,238,951,412,533,637,490,643,69,87,121,126,736,438,110,675,129,595,540,814,452,107,345},{1105,111,873,101,802,860,491,187,454,72,128,857,595,756,337,581,167,414,44,635,492,824,87,157,944,672,39,100,466,489,435,912,590,650,355,382,500,189,911,945,251,30,144,188,128,823,759,285,579,146},{1102,61,960,339,208,894,1001,589,984,809,68,761,711,1000,754,409,373,244,588,274,531,181,646,17,359,764,830,461,391,399,597,643,450,547,324,1000,432,315,580,758,115,990,862,168,981,606,919,696,192,849},{1102,66,20,949,73,370,55,894,173,789,283,760,774,76,297,89,418,71,746,988,172,203,321,24,714,292,972,623,978,154,815,280,210,825,571,626,537,617,510,700,748,135,802,512,553,442,943,962,503,680},{1102,17,225,603,383,929,237,345,895,205,842,700,476,394,867,389,10,747,996,862,437,86,340,230,589,883,662,874,187,507,544,822,867,112,767,240,31,995,928,268,190,112,310,1008,496,168,388,497,905,726},{1101,684,803,31,256,734,257,260,598,786,110,485,598,967,587,356,549,960,341,819,219,873,921,519,872,408,29,250,247,276,318,938,303,111,312,549,187,559,800,776,335,252,251,276,561,828,622,100,778,305},{1102,339,168,173,201,30,923,220,622,160,487,283,441,780,736,743,671,914,644,813,680,321,55,273,587,606,443,551,49,563,198,301,893,357,464,84,729,378,646,694,880,475,967,311,597,693,396,259,597,382},{1102,619,46,460,234,975,56,19,517,447,573,57,738,808,404,545,234,124,913,870,808,135,336,117,789,923,800,527,524,740,900,577,701,936,379,926,901,425,287,760,863,850,808,943,648,554,478,224,20,733},{1105,818,859,763,277,638,676,68,507,543,150,397,110,841,675,831,109,919,246,387,669,451,579,819,737,570,364,205,784,374,929,859,535,130,612,802,110,631,212,607,164,352,347,616,536,12,789,987,921,25},{1104,933,819,934,94,546,494,800,93,620,165,364,470,42,484,424,186,936,397,741,534,903,83,871,861,961,225,640,939,137,1008,293,412,169,569,496,57,405,287,140,16,794,495,828,178,321,242,354,248,982},{1105,772,875,511,985,79,462,200,709,391,679,59,27,433,218,938,920,265,334,549,396,692,333,233,510,853,896,94,197,134,66,625,248,284,478,223,353,930,766,404,664,787,454,33,211,662,961,473,270,637},{1102,1008,319,687,231,819,530,117,904,69,594,312,684,832,586,152,398,281,425,506,676,431,635,472,454,836,124,405,299,736,385,653,734,694,330,307,856,202,767,102,262,351,404,288,525,333,783,265,604,198},{1101,622,971,387,84,415,565,551,162,207,629,537,850,354,574,523,651,420,715,408,512,319,101,906,598,617,581,371,872,176,911,976,140,872,705,567,277,260,460,781,809,79,309,1002,775,873,515,417,283,572},{1107,137,882,259,33,822,866,605,183,80,123,84,46,605,946,741,514,565,344,964,336,143,386,987,135,151,202,992,558,827,907,716,954,131,317,330,943,173,277,116,243,390,542,632,337,478,715,842,385,49},{1108,711,535,524,41,660,18,233,995,566,51,892,624,995,13,931,315,946,446,934,404,32,666,936,654,994,756,359,178,131,751,316,184,276,183,215,278,191,439,615,99,832,497,66,169,852,339,827,140,776},{1101,534,150,408,812,794,392,558,495,560,31,588,218,206,854,391,763,475,924,192,80,14,366,920,70,526,114,399,343,245,517,84,769,657,482,924,441,216,472,279,118,846,857,327,42,54,60,147,871,975},{1102,293,979,38,555,391,554,660,780,887,895,288,314,1006,287,786,920,71,345,735,340,453,571,539,122,955,935,173,92,148,490,116,432,459,145,977,192,689,979,314,919,864,592,223,213,222,351,123,283,686},{1100,613,130,113,494,242,410,772,757,493,910,237,599,332,38,86,652,220,766,621,525,27,828,459,240,31,671,581,496,944,258,687,899,730,790,384,962,191,146,710,26,46,937,967,721,966,396,715,528,152},{1108,43,521,496,493,751,517,506,674,356,441,922,33,330,642,165,56,947,698,544,647,66,933,926,376,644,234,762,701,753,904,369,786,415,856,621,508,715,118,172,61,901,437,436,221,421,944,620,358,632},{1104,347,41,77,264,407,63,840,159,754,583,405,114,712,162,312,675,660,17,783,174,421,26,601,847,590,365,133,200,713,108,344,403,139,764,1009,536,169,839,685,914,765,432,370,467,584,672,484,586,679},{1100,750,442,626,694,632,206,49,755,396,104,853,83,849,982,189,848,860,348,678,887,252,785,309,964,594,883,626,68,459,648,668,552,80,285,588,54,833,979,800,220,73,995,645,265,320,824,103,170,514},{1103,48,109,898,347,63,482,573,680,893,374,670,551,916,92,178,846,137,1002,815,279,564,231,264,199,486,926,365,579,87,869,45,477,968,933,166,22,758,729,44,641,446,56,182,704,138,703,541,617,695},{1106,886,249,919,493,438,395,409,145,317,838,1004,352,305,963,627,814,327,375,885,713,358,321,759,883,16,887,576,547,495,613,235,723,852,145,206,632,882,958,767,541,138,761,883,434,66,853,590,735,570},{1105,438,919,777,187,144,783,65,710,672,902,313,897,967,507,384,164,129,257,464,238,788,592,341,14,368,750,209,948,475,769,756,904,30,523,81,164,648,488,216,662,380,871,549,338,368,276,844,839,875},{1100,419,653,232,751,1009,943,491,560,233,956,320,979,850,692,492,274,199,482,752,405,134,475,619,26,155,977,292,341,159,157,981,568,152,555,661,504,488,142,54,712,441,716,681,633,751,516,897,940,340},{1100,687,465,105,296,833,602,616,115,933,117,614,904,675,756,801,679,250,280,811,647,334,594,353,1005,218,94,863,105,376,194,87,54,1001,534,692,824,126,650,281,49,757,885,295,775,631,87,444,224,709},{1107,861,33,182,556,380,742,641,234,837,359,770,915,403,761,791,86,927,908,78,198,299,826,73,585,591,46,14,377,260,713,964,463,736,488,362,106,220,345,682,48,694,794,305,88,545,438,516,814,336},{1104,1002,626,752,417,553,685,454,909,52,56,612,1007,510,690,837,862,138,48,197,811,438,233,595,733,311,483,513,169,639,840,96,632,808,838,39,703,514,835,602,908,882,556,905,734,236,733,938,364,123},{1107,517,551,700,455,626,1002,280,129,513,909,311,599,531,109,780,913,154,636,738,746,534,962,292,782,686,870,857,614,225,970,81,84,511,772,529,479,116,799,598,619,51,900,561,572,351,683,827,496,309},{1108,232,185,860,867,957,537,727,156,141,294,116,213,721,969,327,240,438,433,382,379,394,423,269,297,337,610,970,507,448,621,405,23,797,255,880,96,782,949,243,266,586,701,821,297,661,138,879,441,561},{1101,810,297,16,421,585,696,374,897,193,812,509,588,825,648,833,47,86,958,987,319,214,563,363,25,850,366,505,719,797,408,313,950,695,671,713,270,357,77,510,540,880,1009,118,47,647,294,85,723,242}};
        int[][] totalAlt = new int[heightMap.length][heightMap[0].length];
        t.solve(heightMap, totalAlt);
        int waterSum = t.trapRainWater(heightMap);
        print(totalAlt);
        System.out.println(waterSum);
    }

}
