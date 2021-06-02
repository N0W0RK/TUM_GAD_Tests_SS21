package tests.radix;

import gad.radix.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class RadixsortTester {

    @BeforeEach
    void printLine() {
        System.out.println("-------------------------");
    }

    /**
     * Tests to check if the the correct key is being returned and the exceptions are being throwned or not
     * @author Aamin
     */
    @Test
    public void keyTests() {
        assertDoesNotThrow(() -> RadixSort.key(0, 0));
        assertDoesNotThrow(() -> RadixSort.key(1, 0));
        assertDoesNotThrow(() -> RadixSort.key(2, 1));
        assertDoesNotThrow(() -> RadixSort.key(3, 3));
        assertDoesNotThrow(() -> RadixSort.key(3, 4));
        assertDoesNotThrow(() -> RadixSort.key(2, 4));
        assertDoesNotThrow(() -> RadixSort.key(1, 4));
        assertThrows(IllegalArgumentException.class, () -> RadixSort.key(-10, 4));
        assertThrows(IllegalArgumentException.class, () -> RadixSort.key(-10, -69));
        assertThrows(IllegalArgumentException.class, () -> RadixSort.key(-69, -420));
        assertEquals(6, RadixSort.key(6467677, 6));
        assertEquals(7, RadixSort.key(6701586, 5));
        assertEquals(6, RadixSort.key(1584166, 1));
        assertEquals(3, RadixSort.key(3355384, 2));
        assertEquals(0, RadixSort.key(5110827, 7));
        assertEquals(9, RadixSort.key(9034065, 6));
        assertEquals(8, RadixSort.key(187831, 2));
        assertEquals(0, RadixSort.key(2980427, 3));
        assertEquals(4, RadixSort.key(8442040, 5));
        assertEquals(8, RadixSort.key(3860898, 5));
        assertEquals(0, RadixSort.key(760179, 6));
        assertEquals(1, RadixSort.key(9000117, 1));
        assertEquals(0, RadixSort.key(9391749, 8));
        assertEquals(0, RadixSort.key(8507017, 2));
        assertEquals(3, RadixSort.key(8293743, 0));
        assertEquals(0, RadixSort.key(9555976, 8));
        assertEquals(1, RadixSort.key(7470164, 2));
        assertEquals(6, RadixSort.key(6231971, 6));
        assertEquals(8, RadixSort.key(7460384, 1));
        assertEquals(1, RadixSort.key(4788116, 1));
        assertEquals(4, RadixSort.key(397442, 2));
        assertEquals(3, RadixSort.key(1020023, 0));
        assertEquals(0, RadixSort.key(3476682, 7));
        assertEquals(6, RadixSort.key(9661273, 5));
        assertEquals(5, RadixSort.key(9355296, 3));
        assertEquals(3, RadixSort.key(1399711, 5));
        assertEquals(8, RadixSort.key(9872328, 5));
        assertEquals(0, RadixSort.key(872600, 1));
        assertEquals(9, RadixSort.key(9027021, 6));
        assertEquals(8, RadixSort.key(3967958, 0));
        assertEquals(1, RadixSort.key(7273721, 0));
        assertEquals(6, RadixSort.key(1116521, 3));
        assertEquals(9, RadixSort.key(7208591, 1));
        assertEquals(0, RadixSort.key(1017381, 8));
        assertEquals(5, RadixSort.key(7977515, 2));
        assertEquals(9, RadixSort.key(9638994, 1));
        assertEquals(0, RadixSort.key(4385297, 8));
        assertEquals(3, RadixSort.key(9385249, 5));
        assertEquals(5, RadixSort.key(8585454, 5));
        assertEquals(5, RadixSort.key(1892580, 2));
        assertEquals(4, RadixSort.key(6179406, 2));
        assertEquals(2, RadixSort.key(6067623, 1));
        assertEquals(5, RadixSort.key(6722754, 1));
        assertEquals(3, RadixSort.key(6559103, 0));
        assertEquals(0, RadixSort.key(468209, 7));
        assertEquals(2, RadixSort.key(8724884, 4));
        assertEquals(0, RadixSort.key(2319011, 8));
        assertEquals(5, RadixSort.key(9303495, 0));
        assertEquals(0, RadixSort.key(7152244, 7));
        assertEquals(0, RadixSort.key(2589762, 7));
        assertEquals(4, RadixSort.key(5414994, 0));
        assertEquals(5, RadixSort.key(8005181, 3));
        assertEquals(8, RadixSort.key(1504078, 0));
        assertEquals(9, RadixSort.key(7669919, 0));
        assertEquals(3, RadixSort.key(3488214, 6));
        assertEquals(0, RadixSort.key(2398786, 7));
        assertEquals(0, RadixSort.key(1038640, 0));
        assertEquals(9, RadixSort.key(675239, 0));
        assertEquals(0, RadixSort.key(9024603, 7));
        assertEquals(6, RadixSort.key(9334166, 0));
        assertEquals(0, RadixSort.key(5240504, 1));
        assertEquals(0, RadixSort.key(3634279, 7));
        assertEquals(5, RadixSort.key(4504145, 0));
        assertEquals(6, RadixSort.key(9816997, 3));
        assertEquals(0, RadixSort.key(550775, 7));
        assertEquals(5, RadixSort.key(3952385, 0));
        assertEquals(1, RadixSort.key(1501457, 3));
        assertEquals(0, RadixSort.key(4971172, 7));
        assertEquals(3, RadixSort.key(144032, 1));
        assertEquals(3, RadixSort.key(7803021, 3));
        assertEquals(8, RadixSort.key(2970178, 0));
        assertEquals(1, RadixSort.key(5136377, 5));
        assertEquals(6, RadixSort.key(2633680, 5));
        assertEquals(8, RadixSort.key(6620687, 1));
        assertEquals(8, RadixSort.key(7885505, 5));
        assertEquals(3, RadixSort.key(2383028, 3));
        assertEquals(0, RadixSort.key(7415504, 1));
        assertEquals(5, RadixSort.key(5981956, 6));
        assertEquals(1, RadixSort.key(1714887, 4));
        assertEquals(7, RadixSort.key(3403597, 0));
        assertEquals(0, RadixSort.key(9216737, 7));
        assertEquals(2, RadixSort.key(6223370, 5));
        assertEquals(5, RadixSort.key(6735611, 3));
        assertEquals(0, RadixSort.key(3219524, 8));
        assertEquals(9, RadixSort.key(4884935, 2));
        assertEquals(4, RadixSort.key(1074395, 3));
        assertEquals(2, RadixSort.key(6892881, 3));
        assertEquals(0, RadixSort.key(6876260, 8));
        assertEquals(3, RadixSort.key(8493755, 3));
        assertEquals(0, RadixSort.key(379685, 6));
        assertEquals(8, RadixSort.key(5980949, 4));
        assertEquals(0, RadixSort.key(6803245, 4));
        assertEquals(1, RadixSort.key(7301639, 3));
        assertEquals(3, RadixSort.key(7648423, 0));
        assertEquals(1, RadixSort.key(4661210, 3));
        assertEquals(2, RadixSort.key(7660252, 0));
        assertEquals(8, RadixSort.key(8198807, 3));
        assertEquals(4, RadixSort.key(7214262, 3));
        assertEquals(2, RadixSort.key(2170220, 1));
        assertEquals(3, RadixSort.key(8536234, 4));
    }

    /**
     * Tests to check if MaxDecimalPlaces returns a correct value or not (also with the edge cases from Artemis)
     * @author Aamin
     */
    @Test
    public void getMaxDecimalPlacesTests() {
        int[] array = null;
        String baseMessage = "Are you sure you're covering all the edge cases, cause apparently you forgot the one where array is ";
        assertEquals(0, RadixSort.getMaxDecimalPlaces(array), baseMessage + " null");
        array = new int[] {};
        assertEquals(0, RadixSort.getMaxDecimalPlaces(array), baseMessage + " empty");
        array = new int[0];
        assertEquals(0, RadixSort.getMaxDecimalPlaces(array), baseMessage + " empty");
        array = new int[] {0};
        assertEquals(1, RadixSort.getMaxDecimalPlaces(array), baseMessage + " having its max element as 0");
        array = new int[] {1, 0, 0, 0, 1, 0};
        assertEquals(1, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {0, 2, 1, 1, 3, 1, 1, 1, 3, 1, 0};
        assertEquals(1, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {8, 6, 3, 0, 2, 4, 9, 4, 9, 5, 3, 1, 2, 0, 3, 7};
        assertEquals(1, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {11, 6, 23, 17, 10, 8, 20, 23, 8, 9, 0, 11, 25, 22, 15, 12, 4, 5, 21, 23, 13};
        assertEquals(2, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {60, 19, 5, 13, 0, 51, 4, 43, 63, 51, 28, 65, 16, 13, 13, 58, 65, 44, 4, 8, 58, 29, 20, 38, 4, 61};
        assertEquals(2, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {107, 109, 198, 81, 28, 167, 3, 200, 62, 39, 142, 43, 7, 184, 59, 135, 173, 144, 36, 46, 185, 17, 66, 62, 188, 169, 67, 116, 119, 52, 25};
        assertEquals(3, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {34, 462, 447, 25, 282, 38, 156, 442, 17, 174, 510, 518, 200, 31, 326, 151, 197, 101, 228, 134, 97, 534, 35, 239, 99, 501, 109, 380, 449, 123, 229, 350, 174, 41, 206, 195};
        assertEquals(3, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {1299, 985, 499, 772, 294, 1353, 155, 73, 257, 781, 244, 435, 877, 702, 326, 1365, 1112, 880, 1117, 54, 250, 1466, 442, 1399, 1320, 695, 1274, 458, 1351, 1489, 106, 818, 361, 1139, 909, 1161, 964, 187, 27, 78, 740};
        assertEquals(4, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {736, 270, 2282, 1036, 1007, 3026, 3538, 1351, 1458, 3477, 3843, 389, 1751, 2450, 2522, 825, 3165, 2442, 512, 3644, 2641, 1593, 3423, 1461, 2750, 2476, 851, 2937, 3973, 2240, 1432, 2511, 484, 3575, 100, 2763, 3094, 3187, 130, 1068, 433, 1623, 4025, 3087, 1900, 3090};
        assertEquals(4, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {2740, 1139, 6408, 3670, 5149, 9565, 3235, 5704, 5067, 5593, 286, 7020, 3358, 5288, 8171, 4436, 1511, 5391, 8725, 3095, 4441, 7887, 3925, 1764, 8317, 3246, 4139, 5514, 7687, 6306, 427, 10009, 6315, 6704, 8085, 5218, 3457, 8011, 3887, 8727, 4526, 10360, 7397, 4262, 5925, 7465, 6547, 1172, 1341, 4579, 9650};
        assertEquals(5, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {27257, 16899, 11313, 22063, 28122, 21456, 5113, 24882, 10468, 20543, 17501, 26700, 19663, 22577, 28253, 8534, 19380, 12546, 24524, 13921, 24794, 21736, 23401, 2265, 20499, 16478, 16907, 26496, 7649, 3654, 10443, 23880, 6997, 19963, 20840, 4589, 28840, 23670, 7823, 29835, 28596, 24604, 8434, 11995, 7364, 26908, 24646, 17037, 10361, 20762, 24322, 6192, 20754, 23250, 17710, 25859};
        assertEquals(5, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {55230, 38797, 38669, 8682, 20303, 49568, 35939, 22172, 26023, 22511, 68719, 46552, 4158, 32084, 20558, 23336, 77848, 24895, 79478, 24995, 9681, 19341, 70666, 33862, 21014, 76555, 44170, 56032, 73952, 60405, 28281, 79096, 79890, 78517, 54797, 80526, 9157, 62818, 68042, 43181, 31919, 67739, 29055, 11920, 57567, 57552, 33150, 21924, 5874, 3700, 80017, 33738, 22967, 12977, 50055, 6316, 48463, 28317, 24461, 35607, 43996};
        assertEquals(5, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {71296, 11069, 82413, 157827, 15107, 113107, 36228, 164184, 128638, 24332, 215560, 92001, 35981, 186620, 24711, 23501, 84405, 208000, 139025, 94031, 215611, 128302, 149039, 35322, 196532, 93623, 128989, 50972, 22944, 215442, 195086, 125123, 125136, 84642, 36989, 83452, 73160, 127414, 53050, 185369, 192231, 117873, 117910, 149703, 72952, 46610, 41331, 107388, 211341, 151963, 33830, 8651, 3888, 53904, 137974, 208047, 102764, 89018, 180638, 101002, 207803, 71741, 207246, 116506, 129000, 187564};
        assertEquals(6, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {596693, 87293, 428351, 58529, 454301, 466350, 24928, 338269, 411484, 207532, 31874, 32919, 326444, 80446, 323642, 573392, 538792, 524246, 565969, 49782, 436877, 107073, 96483, 516054, 306788, 320281, 468790, 200736, 12998, 491488, 569797, 137057, 364885, 76929, 281336, 486223, 179200, 378849, 518079, 321886, 464983, 479859, 5106, 570938, 513905, 422088, 152291, 301208, 39943, 151767, 291846, 510316, 472216, 308010, 537222, 339143, 323165, 188759, 120010, 292875, 70101, 58974, 438367, 220895, 304690, 297082, 413395, 114476, 144434, 69208, 337576};
        assertEquals(6, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {1086573, 1021197, 811233, 291189, 894129, 421854, 306392, 555814, 460442, 84209, 1237070, 265332, 1425114, 1309309, 786740, 1277013, 865485, 403632, 1149006, 435863, 114234, 397825, 701190, 1042416, 80011, 1030922, 28614, 1375522, 835139, 384054, 138741, 913377, 743343, 21538, 1200831, 541685, 944823, 1108002, 1412832, 561934, 684185, 144134, 863063, 937390, 1491738, 102047, 785992, 1294672, 255748, 1088497, 192205, 398815, 375134, 1537717, 372275, 6900, 1581973, 1615094, 137109, 1062171, 376377, 1465567, 589272, 923659, 1555034, 480502, 1139574, 276510, 1245566, 904966, 1503770, 1319603, 1537847, 268085, 679812, 469053};
        assertEquals(7, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {1614414, 2417955, 3874016, 603453, 1054713, 2458292, 863805, 1422202, 3521277, 393289, 3796006, 1900125, 3832047, 56126, 2471405, 3534041, 4152860, 94299, 4429335, 1260062, 1453325, 1500205, 419727, 3649870, 2566592, 18346, 2594198, 3302748, 2012426, 4392104, 2760355, 664348, 3518340, 3641418, 1747878, 207524, 1121679, 2112324, 3912782, 143872, 1977164, 14789, 3104315, 2738215, 899723, 4381132, 2172106, 2973188, 3811346, 514987, 3373949, 3824833, 3666168, 4050971, 4012934, 1385586, 2997255, 2641306, 4092336, 3822029, 2622941, 2033405, 1070181, 3040218, 609579, 2515665, 1809385, 3890225, 235158, 4369084, 2976951, 4205704, 4000988, 2689057, 1939950, 4360720, 3583473, 657124, 261231, 4242864, 2634176};
        assertEquals(7, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {10693515, 11172208, 6088290, 201185, 5623843, 8417916, 6685186, 5748518, 11598396, 9510285, 2820386, 5121222, 7003960, 3408346, 10228890, 3107833, 6563981, 2198789, 6136145, 7261221, 11479506, 2898763, 10094065, 2676970, 7804595, 10041293, 10619989, 6284970, 4999687, 11420031, 8727292, 3050066, 5016564, 7224336, 7667885, 24372, 1637686, 8082683, 4359146, 6339157, 4837719, 6945695, 11719236, 10001495, 2991964, 10925224, 5045234, 6790788, 7808561, 7021730, 5276559, 3744298, 22859, 2930679, 10140091, 6808099, 9704184, 5256297, 10980979, 11792060, 11080968, 5493479, 10858338, 6481553, 7171138, 7425436, 3686795, 8768282, 2855331, 1361258, 10669450, 6943840, 2703084, 7354814, 4794195, 8387766, 4050220, 3285390, 6261503, 4158234, 4478351, 9741640, 986589, 1619068, 8811540, 5753743};
        assertEquals(8, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {12296160, 23436775, 2867280, 30576411, 17549075, 27448246, 19050933, 21180981, 32072347, 6167697, 26457543, 6123845, 31187515, 21435724, 9303234, 27454948, 2119938, 19725791, 23312553, 2151432, 17773192, 27327320, 31094990, 4930939, 14814691, 26284661, 14446895, 13657351, 8985567, 1177831, 134242, 13551358, 3442389, 32202175, 26561091, 11607984, 31915420, 583858, 10342719, 30704334, 20496168, 20494116, 5063524, 10839846, 1406947, 26014584, 1762182, 32380626, 5209633, 31931177, 2113753, 22006901, 26293771, 27804790, 29780868, 27803021, 6951251, 27890864, 23530617, 16730849, 908841, 10885694, 7864251, 12659283, 12636390, 5368113, 17747082, 27916979, 23092487, 30632643, 6491670, 18226642, 15052385, 29084390, 536937, 5602275, 5613388, 6271168, 15478297, 18807010, 30736434, 23000701, 14448820, 13569501, 24989240, 30317183, 13301235, 13190905, 29170982, 13170102, 18883280};
        assertEquals(8, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {19773219, 86874093, 22058881, 85305168, 173716, 87764403, 21529025, 30085185, 60659460, 30088134, 63366398, 66421738, 79679950, 29922916, 18003236, 55567022, 83600860, 81381153, 47238320, 9025822, 21842448, 56046694, 82210753, 76361990, 85240000, 25215051, 36302890, 35407665, 21518756, 33274748, 20188539, 83368377, 39195989, 74445784, 49478499, 41681592, 54938887, 86111683, 19019703, 38069048, 11354721, 69887864, 85788145, 78076979, 54292355, 80217333, 48745289, 73801992, 22022523, 36756549, 76275736, 12339405, 48855702, 88277194, 36035475, 28538864, 3468341, 39324522, 32103236, 50865837, 19275180, 5525304, 32127838, 50902118, 72675021, 19111861, 82067172, 14838418, 35632588, 27022222, 63670655, 76969239, 63165954, 87728704, 79664203, 63532641, 71391627, 71316052, 88752581, 82463042, 36536258, 32748270, 69697061, 14587517, 306933, 35199523, 44687778, 7231758, 63748316, 22037928, 32316579, 77115251, 78481140, 4011356, 66754664, 7236581};
        assertEquals(8, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {134749181, 119326690, 156382009, 20065227, 76161988, 160283257, 12940772, 65810374, 120195853, 228748641, 129999628, 230791325, 21656564, 236291919, 85976102, 73118861, 104707125, 234118056, 25941539, 116896107, 133486741, 239789407, 89717359, 205257737, 143835130, 111770366, 111891690, 136590271, 175659621, 19638438, 47670817, 59345934, 172573316, 214923921, 85826032, 38349640, 28479461, 161615891, 125927904, 242243575, 152114260, 184092216, 149232006, 93574449, 160919285, 9960941, 48173729, 84388304, 225439990, 222886114, 120658299, 158009656, 29103154, 83902634, 108744998, 25624183, 192888467, 181031128, 178130288, 100375385, 159656552, 11265949, 43915224, 3201658, 138386180, 189019232, 82559497, 12082378, 205667011, 104764514, 66346020, 150877867, 215396916, 24988886, 1324347, 28149102, 38901180, 238245225, 75995668, 3335521, 165753661, 220460670, 117050645, 85738555, 220646073, 100463400, 147410285, 135807707, 177780269, 85637773, 145025429, 242182503, 101540650, 111888333, 194213644, 49895193, 45081247, 205760792, 107114158, 180771169, 229089755};
        assertEquals(9, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {230623300, 149159096, 144222922, 381385342, 201715533, 225443074, 486766758, 204271458, 545095772, 185707057, 373906172, 133870104, 547248799, 367909568, 179687597, 576127263, 262003545, 231560243, 42434672, 519941093, 570722525, 633555814, 625093729, 464052761, 293135410, 361519770, 628890811, 506234619, 112082644, 85519177, 160081554, 298739999, 129789753, 131880740, 603702893, 193904667, 149565292, 252837230, 90969079, 336593274, 543283326, 594833105, 319199601, 527394619, 400548573, 475248787, 124610546, 518739457, 196849751, 96658432, 323985407, 3289648, 648368301, 192038756, 539607455, 75226334, 133781548, 150466825, 440249567, 24945231, 548703756, 134588877, 466163009, 470572322, 289525635, 658706819, 28176629, 404572235, 248684934, 487573176, 178125152, 334708631, 404628646, 426348501, 628687852, 235657636, 653615863, 428671769, 568321323, 147662391, 559404223, 588051536, 262891500, 220125341, 202999772, 559855697, 476912257, 509576959, 335859049, 154168800, 441638473, 612583188, 77634058, 614433858, 400181028, 489544345, 473733656, 93055987, 10550685, 105975682, 592174309, 265051314, 92402402, 330421167, 102269576, 68540577};
        assertEquals(9, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {227161077, 434631353, 625746513, 298796013, 27686553, 706353745, 467570060, 974795479, 522031401, 588209160, 847675102, 303211651, 779404622, 954410693, 522129022, 105127996, 102449569, 1012708422, 339113320, 631558283, 307262664, 695895185, 675882980, 180905262, 258978933, 152010358, 536253886, 1071516113, 1047095738, 995584671, 777296101, 769518075, 640468866, 432923917, 771596446, 861935459, 769497256, 769342993, 30481128, 115097688, 129168913, 73316542, 747735182, 289425255, 404495913, 426335882, 97896133, 607994033, 974610625, 588268606, 212974573, 174559966, 187484709, 1000848873, 475393021, 418805305, 968827168, 1008365055, 810559351, 66002342, 5637844, 553987463, 425475355, 982076049, 59078268, 468465293, 947697976, 679684806, 1029489419, 750513291, 11004859, 555356564, 653586267, 757267532, 329731981, 111829538, 980867072, 231831616, 485371136, 946674988, 25665116, 567292480, 373640860, 613567557, 589019387, 993581487, 908045754, 592210983, 233805948, 746374724, 318353792, 165273702, 517324619, 126386944, 1046856021, 59615884, 707456524, 728255667, 509875, 318243300, 120085408, 384655152, 652566328, 907097008, 1039316863, 972522069, 826621730, 617390854, 106735663, 1071097010, 50567542};
        assertEquals(10, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {936267847, 168262809, 865303239, 596209796, 1027410828, 804224955, 1069822412, 698659123, 317534961, 492564928, 629365275, 301109603, 488350243, 69374985, 845330977, 268430296, 640049234, 742120777, 117391007, 1013250392, 429394297, 555279937, 832613227, 38499117, 934856193, 52886718, 572956685, 705860178, 722370757, 667860462, 939032371, 919040875, 729094043, 843825958, 4249068, 722988848, 898315647, 513627969, 725857330, 248347903, 964178790, 384702957, 696980089, 103746526, 150097774, 814303384, 694128905, 583596825, 653577138, 555073616, 418881315, 152875349, 1073137540, 574751947, 67264761, 795495264, 753787365, 258169724, 1015484856, 777759199, 66279887, 287148283, 118643479, 765727780, 273526075, 433511420, 446100412, 884390859, 219055337, 339046063, 131547951, 643823591, 208503711, 1054012119, 605927533, 312226623, 278510747, 4241862, 518707485, 1060979686, 917400483, 443699516, 610980908, 781212208, 876491377, 618231579, 499395503, 709636364, 41795089, 183274978, 643947406, 533621139, 515816153, 330062236, 958859725, 756926179, 735500871, 186235131, 136485663, 73182458, 710276456, 232937259, 959923599, 851128871, 999484464, 394324112, 846865719, 251970374, 250969170, 766151037, 413884196, 77641143, 513231127, 312571862, 144157092, 991346582};
        assertEquals(10, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {154496628, 945939701, 977049100, 429606034, 795584168, 278535928, 963780600, 578059691, 505191062, 129437067, 84440998, 390870801, 163692138, 496682146, 606355672, 179909089, 722129578, 935461848, 642434143, 224982534, 106633846, 142748349, 436749394, 491400781, 991795444, 228618986, 723855903, 43382782, 326174974, 1028547787, 764236153, 386643934, 802305599, 289477512, 72530787, 212873278, 749825380, 638236003, 134734916, 735778159, 694265414, 464205866, 732576927, 608258441, 336177971, 199971294, 1056040131, 611014141, 320776688, 608094682, 732442222, 209507427, 476656164, 123261005, 141374422, 850543629, 26740409, 190324842, 566299687, 116669245, 698192473, 788790906, 576086933, 57084176, 190823564, 71435582, 1029828872, 317677244, 102777256, 821121858, 751527229, 42433055, 810040583, 674115935, 86191333, 450783735, 190829866, 843207278, 281708836, 730957542, 391090994, 602431572, 1011466110, 266735948, 585376144, 793990107, 1031543084, 276805720, 518909257, 760715863, 805713206, 445494866, 620707312, 304505876, 748936678, 468520580, 315091210, 135553734, 1038009936, 137318097, 29943705, 641228454, 879062302, 793114117, 121375495, 537462456, 498883933, 364823172, 440700024, 956327587, 260228703, 258126836, 294539752, 36904852, 786930295, 662961341, 748928797, 16929277, 109653443, 847191704, 411402992};
        assertEquals(10, RadixSort.getMaxDecimalPlaces(array));
        array = new int[] {184305422, 570829666, 887775336, 230209307, 31933409, 167257174, 993839939, 730078062, 1051003462, 746325134, 119459520, 173882027, 196289206, 533446132, 328228577, 928698521, 133692710, 170885626, 1029781156, 656289880, 282636812, 162335289, 249663226, 841065694, 934165179, 523839696, 693508029, 359007760, 357018270, 707572314, 302139183, 545647623, 293726956, 904665479, 1037100225, 747973741, 444517192, 575799507, 9209279, 296765932, 291749951, 735031063, 816715570, 83500975, 194662027, 77642836, 683046025, 551740319, 46502503, 146922182, 453772507, 624710746, 586446442, 1041670316, 768280781, 294412065, 579660803, 57404125, 710488823, 302117901, 114245268, 766380705, 231086854, 786367154, 662067125, 889342164, 1003490536, 524130565, 777831942, 329800888, 580743058, 885232824, 730878209, 1048052541, 896207421, 613369856, 1003500992, 560421168, 517544141, 849190635, 329038844, 1055622250, 710859070, 179012737, 155110168, 608581475, 200524227, 210456391, 236819402, 205003507, 79886547, 420781141, 152099892, 760747203, 986633412, 947730587, 970915960, 785708941, 48969430, 1020671417, 539593214, 1023303263, 1042757429, 429046266, 923964312, 566261847, 353117101, 317281492, 782947854, 814836227, 546474523, 295863448, 113428565, 467471811, 460381752, 862376978, 860299347, 799527167, 303353958, 92181814, 696606142, 221926043, 888466278, 432326465, 505078050, 23866976};
        assertEquals(10, RadixSort.getMaxDecimalPlaces(array));
    }

    /**
     * Tests to see if you're doing the concatenation of artemis correctly or not
     * @author Aamin
     */
    @Test
    public void concatenateArtemis() {
        ArrayList<Integer>[] buckets = new ArrayList[3];

        for (int i = 0; i < 3; i++) {
            buckets[i] = new ArrayList<>();
        }

        buckets[0].add(3);
        buckets[0].add(4);
        buckets[0].add(6);

        buckets[1].add(9);
        buckets[1].add(3);
        buckets[1].add(9);

        buckets[2].add(4);
        buckets[2].add(2);
        buckets[2].add(0);

        int[] element = new int[9];
        RadixSort.concatenate(buckets, element);
        assertArrayEquals(new int[] {3, 4, 6, 9, 3, 9, 4, 2, 0}, element);
    }

    /**
     * Tests to see if in general you're doing the concatenation correctly (incl edge cases)
     * @author Aamin
     */
    @Test
    public void concatenateTests() {
        ArrayList<Integer>[] buckets;
        int[] elements;
        buckets = new ArrayList[2];
        for (int i = 0; i < 2; i++) {
            buckets[i] = new ArrayList<>();
        }
        {
            buckets[0].addAll(Arrays.stream(new int[] {}).boxed().toList());
            buckets[1].addAll(Arrays.stream(new int[] {}).boxed().toList());
        }
        elements = new int[] {};
        RadixSort.concatenate(buckets, elements);
        assertArrayEquals(new int[]{}, elements);

        buckets = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            buckets[i] = new ArrayList<>();
        }
        {
            buckets[0].addAll(Arrays.stream(new int[] {}).boxed().toList());
            buckets[1].addAll(Arrays.stream(new int[] {}).boxed().toList());
            buckets[2].addAll(Arrays.stream(new int[] {}).boxed().toList());
            buckets[3].addAll(Arrays.stream(new int[] {}).boxed().toList());
            buckets[4].addAll(Arrays.stream(new int[] {}).boxed().toList());
        }
        elements = new int[] {};
        RadixSort.concatenate(buckets, elements);
        assertArrayEquals(new int[]{}, elements);

        buckets = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            buckets[i] = new ArrayList<>();
        }
        {
            buckets[0].addAll(Arrays.stream(new int[] {1}).boxed().toList());
            buckets[1].addAll(Arrays.stream(new int[] {3}).boxed().toList());
            buckets[2].addAll(Arrays.stream(new int[] {7}).boxed().toList());
            buckets[3].addAll(Arrays.stream(new int[] {7}).boxed().toList());
        }
        elements = new int[] {1, 3, 7, 7};
        RadixSort.concatenate(buckets, elements);
        assertArrayEquals(new int[]{1, 3, 7, 7}, elements);

        buckets = new ArrayList[2];
        for (int i = 0; i < 2; i++) {
            buckets[i] = new ArrayList<>();
        }
        {
            buckets[0].addAll(Arrays.stream(new int[] {6, 4, 8, 6, 8, 0}).boxed().toList());
            buckets[1].addAll(Arrays.stream(new int[] {8, 4, 7, 0, 2, 6}).boxed().toList());
        }
        elements = new int[] {6, 4, 8, 6, 8, 0, 8, 4, 7, 0, 2, 6};
        RadixSort.concatenate(buckets, elements);
        assertArrayEquals(new int[]{6, 4, 8, 6, 8, 0, 8, 4, 7, 0, 2, 6}, elements);

        buckets = new ArrayList[1];
        for (int i = 0; i < 1; i++) {
            buckets[i] = new ArrayList<>();
        }
        {
            buckets[0].addAll(Arrays.stream(new int[] {9, 5}).boxed().toList());
        }
        elements = new int[] {9, 5};
        RadixSort.concatenate(buckets, elements);
        assertArrayEquals(new int[]{9, 5}, elements);

        buckets = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            buckets[i] = new ArrayList<>();
        }
        {
            buckets[0].addAll(Arrays.stream(new int[] {2, 0, 3}).boxed().toList());
            buckets[1].addAll(Arrays.stream(new int[] {3, 6, 7}).boxed().toList());
            buckets[2].addAll(Arrays.stream(new int[] {0, 6, 3}).boxed().toList());
            buckets[3].addAll(Arrays.stream(new int[] {7, 7, 8}).boxed().toList());
            buckets[4].addAll(Arrays.stream(new int[] {1, 4, 3}).boxed().toList());
            buckets[5].addAll(Arrays.stream(new int[] {8, 2, 5}).boxed().toList());
            buckets[6].addAll(Arrays.stream(new int[] {4, 5, 9}).boxed().toList());
        }
        elements = new int[] {2, 0, 3, 3, 6, 7, 0, 6, 3, 7, 7, 8, 1, 4, 3, 8, 2, 5, 4, 5, 9};
        RadixSort.concatenate(buckets, elements);
        assertArrayEquals(new int[]{2, 0, 3, 3, 6, 7, 0, 6, 3, 7, 7, 8, 1, 4, 3, 8, 2, 5, 4, 5, 9}, elements);

        buckets = new ArrayList[2];
        for (int i = 0; i < 2; i++) {
            buckets[i] = new ArrayList<>();
        }
        {
            buckets[0].addAll(Arrays.stream(new int[] {6, 0}).boxed().toList());
            buckets[1].addAll(Arrays.stream(new int[] {4, 9}).boxed().toList());
        }
        elements = new int[] {6, 0, 4, 9};
        RadixSort.concatenate(buckets, elements);
        assertArrayEquals(new int[]{6, 0, 4, 9}, elements);

        buckets = new ArrayList[9];
        for (int i = 0; i < 9; i++) {
            buckets[i] = new ArrayList<>();
        }
        {
            buckets[0].addAll(Arrays.stream(new int[] {2}).boxed().toList());
            buckets[1].addAll(Arrays.stream(new int[] {1}).boxed().toList());
            buckets[2].addAll(Arrays.stream(new int[] {4}).boxed().toList());
            buckets[3].addAll(Arrays.stream(new int[] {7}).boxed().toList());
            buckets[4].addAll(Arrays.stream(new int[] {7}).boxed().toList());
            buckets[5].addAll(Arrays.stream(new int[] {7}).boxed().toList());
            buckets[6].addAll(Arrays.stream(new int[] {4}).boxed().toList());
            buckets[7].addAll(Arrays.stream(new int[] {1}).boxed().toList());
            buckets[8].addAll(Arrays.stream(new int[] {8}).boxed().toList());
        }
        elements = new int[] {2, 1, 4, 7, 7, 7, 4, 1, 8};
        RadixSort.concatenate(buckets, elements);
        assertArrayEquals(new int[]{2, 1, 4, 7, 7, 7, 4, 1, 8}, elements);

        buckets = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            buckets[i] = new ArrayList<>();
        }
        {
            buckets[0].addAll(Arrays.stream(new int[] {4, 9, 6, 6, 8, 8, 5}).boxed().toList());
            buckets[1].addAll(Arrays.stream(new int[] {2, 6, 8, 7, 4, 7, 1}).boxed().toList());
            buckets[2].addAll(Arrays.stream(new int[] {4, 6, 2, 8, 2, 3, 3}).boxed().toList());
        }
        elements = new int[] {4, 9, 6, 6, 8, 8, 5, 2, 6, 8, 7, 4, 7, 1, 4, 6, 2, 8, 2, 3, 3};
        RadixSort.concatenate(buckets, elements);
        assertArrayEquals(new int[]{4, 9, 6, 6, 8, 8, 5, 2, 6, 8, 7, 4, 7, 1, 4, 6, 2, 8, 2, 3, 3}, elements);

        buckets = new ArrayList[8];
        for (int i = 0; i < 8; i++) {
            buckets[i] = new ArrayList<>();
        }
        {
            buckets[0].addAll(Arrays.stream(new int[] {4, 3}).boxed().toList());
            buckets[1].addAll(Arrays.stream(new int[] {7, 4}).boxed().toList());
            buckets[2].addAll(Arrays.stream(new int[] {5, 0}).boxed().toList());
            buckets[3].addAll(Arrays.stream(new int[] {0, 0}).boxed().toList());
            buckets[4].addAll(Arrays.stream(new int[] {9, 7}).boxed().toList());
            buckets[5].addAll(Arrays.stream(new int[] {4, 0}).boxed().toList());
            buckets[6].addAll(Arrays.stream(new int[] {0, 8}).boxed().toList());
            buckets[7].addAll(Arrays.stream(new int[] {1, 4}).boxed().toList());
        }
        elements = new int[] {4, 3, 7, 4, 5, 0, 0, 0, 9, 7, 4, 0, 0, 8, 1, 4};
        RadixSort.concatenate(buckets, elements);
        assertArrayEquals(new int[]{4, 3, 7, 4, 5, 0, 0, 0, 9, 7, 4, 0, 0, 8, 1, 4}, elements);
    }

    /**
     * Tests to see if your ksort is sorting the respective decimal place correctly or not
     * @author Aamin
     */
    @Test
    public void ksortTests() {
        int[] elements;
        elements = new int[]{0};
        RadixSort.kSort(elements, 1);
        assertArrayEquals(new int[] {0}, elements);

        elements = new int[]{0, 1, 1};
        RadixSort.kSort(elements, 0);
        assertArrayEquals(new int[] {0, 1, 1}, elements);

        elements = new int[]{2, 3, 3, 0, 0};
        RadixSort.kSort(elements, 1);
        assertArrayEquals(new int[] {2, 3, 3, 0, 0}, elements);

        elements = new int[]{5, 2, 4, 3, 3, 3, 7};
        RadixSort.kSort(elements, 0);
        assertArrayEquals(new int[] {2, 3, 3, 3, 4, 5, 7}, elements);

        elements = new int[]{23, 14, 12, 21, 22, 2, 0, 6, 10};
        RadixSort.kSort(elements, 1);
        assertArrayEquals(new int[] {2, 0, 6, 14, 12, 10, 23, 21, 22}, elements);

        elements = new int[]{24, 4, 1, 1, 70, 29, 8, 8, 18, 33, 36};
        RadixSort.kSort(elements, 0);
        assertArrayEquals(new int[] {70, 1, 1, 33, 24, 4, 36, 8, 8, 18, 29}, elements);

        elements = new int[]{20, 130, 153, 76, 138, 121, 188, 15, 51, 59, 168, 3, 162};
        RadixSort.kSort(elements, 4);
        assertArrayEquals(new int[] {20, 130, 153, 76, 138, 121, 188, 15, 51, 59, 168, 3, 162}, elements);

        elements = new int[]{38, 20, 26, 62, 82, 8, 451, 139, 472, 440, 397, 387, 325, 69, 231};
        RadixSort.kSort(elements, 3);
        assertArrayEquals(new int[] {38, 20, 26, 62, 82, 8, 451, 139, 472, 440, 397, 387, 325, 69, 231}, elements);

        elements = new int[]{1366, 834, 346, 647, 1040, 733, 1049, 694, 1103, 945, 1450, 1119, 789, 458, 1181, 1429, 407};
        RadixSort.kSort(elements, 5);
        assertArrayEquals(new int[] {1366, 834, 346, 647, 1040, 733, 1049, 694, 1103, 945, 1450, 1119, 789, 458, 1181, 1429, 407}, elements);

        elements = new int[]{105, 2293, 732, 2335, 515, 3856, 150, 3664, 2689, 1853, 1171, 1512, 2888, 219, 830, 279, 3403, 1400, 667};
        RadixSort.kSort(elements, 2);
        assertArrayEquals(new int[] {105, 150, 1171, 2293, 219, 279, 2335, 3403, 1400, 515, 1512, 3664, 2689, 667, 732, 3856, 1853, 2888, 830}, elements);

        elements = new int[]{5244, 5133, 4767, 7776, 10268, 2740, 2962, 4775, 4795, 2755, 1614, 753, 513, 343, 8440, 6979, 2039, 7911, 6748, 3274, 10277};
        RadixSort.kSort(elements, 0);
        assertArrayEquals(new int[] {2740, 8440, 7911, 2962, 5133, 753, 513, 343, 5244, 1614, 3274, 4775, 4795, 2755, 7776, 4767, 10277, 10268, 6748, 6979, 2039}, elements);

        elements = new int[]{26155, 7159, 15953, 2747, 29901, 19504, 2611, 7070, 17326, 9337, 25685, 22764, 28655, 29280, 10287, 6480, 13397, 28104, 8151, 5896, 11577, 308, 4745};
        RadixSort.kSort(elements, 3);
        assertArrayEquals(new int[] {10287, 308, 11577, 2747, 2611, 22764, 13397, 4745, 15953, 25685, 5896, 26155, 6480, 7159, 7070, 17326, 28655, 28104, 8151, 29901, 19504, 9337, 29280}, elements);

        elements = new int[]{59177, 57521, 75641, 27058, 69687, 21964, 46292, 74661, 31371, 65284, 39404, 44632, 54956, 26980, 52232, 56610, 77463, 41613, 14922, 41485, 65647, 36370, 79088, 67690, 75727};
        RadixSort.kSort(elements, 3);
        assertArrayEquals(new int[] {21964, 31371, 41613, 41485, 52232, 74661, 44632, 54956, 14922, 75641, 65284, 65647, 75727, 46292, 26980, 56610, 36370, 57521, 27058, 77463, 67690, 59177, 69687, 39404, 79088}, elements);

        elements = new int[]{111768, 153287, 186640, 79208, 72330, 2866, 35775, 29926, 113638, 71124, 161866, 191610, 68450, 47617, 34540, 218797, 86889, 147328, 56481, 72245, 177829, 113491, 5591, 94677, 55376, 161120, 103038};
        RadixSort.kSort(elements, 2);
        assertArrayEquals(new int[] {103038, 71124, 161120, 153287, 79208, 72245, 72330, 147328, 55376, 68450, 56481, 113491, 34540, 5591, 186640, 113638, 191610, 47617, 94677, 111768, 35775, 218797, 2866, 161866, 86889, 177829, 29926}, elements);

        elements = new int[]{320179, 231215, 36101, 395953, 288652, 299119, 378275, 326331, 212268, 589178, 53992, 322883, 559066, 156311, 143092, 514627, 310606, 275347, 245392, 154924, 469903, 314437, 586483, 562917, 281389, 2788, 271051, 481059, 167568};
        RadixSort.kSort(elements, 6);
        assertArrayEquals(new int[] {320179, 231215, 36101, 395953, 288652, 299119, 378275, 326331, 212268, 589178, 53992, 322883, 559066, 156311, 143092, 514627, 310606, 275347, 245392, 154924, 469903, 314437, 586483, 562917, 281389, 2788, 271051, 481059, 167568}, elements);

        elements = new int[]{1208522, 732657, 1301893, 413253, 1380464, 512100, 922273, 960811, 677980, 463240, 177757, 1543741, 677402, 606213, 411791, 1040342, 492247, 548155, 46177, 1405493, 777005, 217170, 689994, 84414, 366137, 1215127, 302807, 780834, 117036, 905267, 107312};
        RadixSort.kSort(elements, 4);
        assertArrayEquals(new int[] {1208522, 1301893, 606213, 1405493, 302807, 905267, 107312, 413253, 512100, 411791, 217170, 1215127, 117036, 922273, 732657, 1543741, 1040342, 548155, 46177, 960811, 463240, 366137, 677980, 177757, 677402, 777005, 1380464, 689994, 84414, 780834, 492247}, elements);

        elements = new int[]{3222448, 898871, 2546512, 1764835, 1875107, 3694099, 2417426, 3793481, 2604417, 2961276, 3576434, 2720655, 4076514, 630925, 596182, 1141981, 635112, 4428114, 3195650, 1416814, 3869972, 3648225, 3225398, 2731665, 3212203, 2731155, 1345161, 2350214, 4217668, 3050365, 2747393, 3861203, 363676};
        RadixSort.kSort(elements, 1);
        assertArrayEquals(new int[] {1875107, 3212203, 3861203, 2546512, 2604417, 4076514, 635112, 4428114, 1416814, 2350214, 2417426, 630925, 3648225, 1764835, 3576434, 3222448, 2720655, 3195650, 2731155, 2731665, 1345161, 4217668, 3050365, 898871, 2961276, 3869972, 363676, 3793481, 596182, 1141981, 3694099, 3225398, 2747393}, elements);

        elements = new int[]{11199919, 2478806, 3147001, 11304007, 2246639, 8537, 10485975, 5108662, 3276980, 480284, 5480524, 11800957, 4980637, 6846121, 2994198, 2990519, 4757699, 7125649, 9831480, 3658094, 6015232, 5906681, 7592967, 5825611, 8740787, 4356169, 10559170, 8331824, 8791796, 9470380, 6324120, 5576825, 1707346, 3434360, 3000352};
        RadixSort.kSort(elements, 6);
        assertArrayEquals(new int[] {8537, 10485975, 480284, 10559170, 11199919, 11304007, 11800957, 1707346, 2478806, 2246639, 2994198, 2990519, 3147001, 3276980, 3658094, 3434360, 3000352, 4980637, 4757699, 4356169, 5108662, 5480524, 5906681, 5825611, 5576825, 6846121, 6015232, 6324120, 7125649, 7592967, 8740787, 8331824, 8791796, 9831480, 9470380}, elements);

        elements = new int[]{146703, 27725899, 5364124, 32797882, 19597148, 2305069, 8733025, 1937700, 12485626, 230333, 15604750, 5032908, 13988616, 13122023, 23647814, 1208672, 9684212, 262213, 31363985, 24952244, 30214409, 20772762, 18450295, 24432981, 23481770, 29867439, 9018320, 28721040, 32822503, 19624206, 20580042, 2501895, 17836477, 31026324, 17177987, 20490692, 1565607};
        RadixSort.kSort(elements, 5);
        assertArrayEquals(new int[] {5032908, 9018320, 31026324, 146703, 13122023, 17177987, 230333, 1208672, 262213, 30214409, 5364124, 2305069, 31363985, 12485626, 18450295, 24432981, 23481770, 20490692, 19597148, 20580042, 2501895, 1565607, 15604750, 23647814, 9684212, 19624206, 27725899, 32797882, 8733025, 20772762, 28721040, 29867439, 32822503, 17836477, 1937700, 13988616, 24952244}, elements);

        elements = new int[]{35817943, 65320380, 66884775, 48142362, 70291592, 66605780, 78696740, 63489745, 87662531, 55198743, 75278095, 79930884, 54524510, 86782022, 70380589, 30230582, 46932665, 56487803, 58849770, 22021469, 63784977, 48195448, 66981168, 45779141, 24329058, 55562949, 45359938, 71224006, 44760104, 78455055, 65568067, 82254368, 45348082, 55720202, 59947217, 80531080, 65358383, 83523050, 11844730};
        RadixSort.kSort(elements, 9);
        assertArrayEquals(new int[] {35817943, 65320380, 66884775, 48142362, 70291592, 66605780, 78696740, 63489745, 87662531, 55198743, 75278095, 79930884, 54524510, 86782022, 70380589, 30230582, 46932665, 56487803, 58849770, 22021469, 63784977, 48195448, 66981168, 45779141, 24329058, 55562949, 45359938, 71224006, 44760104, 78455055, 65568067, 82254368, 45348082, 55720202, 59947217, 80531080, 65358383, 83523050, 11844730}, elements);

    }

    /**
     * Tests to see if you are sorting the entire array correctly or not using Radix sort + logging
     * Note: Will be added latter, I'm tired and I need to chill first
     * @author Aamin
     */
    @Test
    public void sort() {

    }

}