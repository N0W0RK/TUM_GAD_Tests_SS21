package gad.simplehash;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class HashtableTest {

    @Test
    public void getNextPowerOfTwo() {
        //TODO IF THE NEGATIVE VALUES DON'T WORK, THAT'S FINE, IT DOESN'T HAVE TO, REMOVE IT IN THAT CASE
        //I GOT THESE OUTPUTS FROM MY IMPLEMENTATION
        assertEquals(1, Hashtable.getNextPowerOfTwo(-4));
        assertEquals(1, Hashtable.getNextPowerOfTwo(-3));
        assertEquals(1, Hashtable.getNextPowerOfTwo(-2));
        assertEquals(1, Hashtable.getNextPowerOfTwo(-1));

        //PROPER VALUES
        assertEquals(1, Hashtable.getNextPowerOfTwo(0));
        assertEquals( 1 , Hashtable.getNextPowerOfTwo( 1 ));
        assertEquals( 2 , Hashtable.getNextPowerOfTwo( 2 ));
        assertEquals( 4 , Hashtable.getNextPowerOfTwo( 3 ));
        assertEquals( 4 , Hashtable.getNextPowerOfTwo( 4 ));
        assertEquals( 8 , Hashtable.getNextPowerOfTwo( 5 ));
        assertEquals( 8 , Hashtable.getNextPowerOfTwo( 6 ));
        assertEquals( 8 , Hashtable.getNextPowerOfTwo( 7 ));
        assertEquals( 8 , Hashtable.getNextPowerOfTwo( 8 ));
        assertEquals( 16 , Hashtable.getNextPowerOfTwo( 9 ));
        assertEquals( 16 , Hashtable.getNextPowerOfTwo( 10 ));
        assertEquals( 16 , Hashtable.getNextPowerOfTwo( 11 ));
        assertEquals( 16 , Hashtable.getNextPowerOfTwo( 12 ));
        assertEquals( 16 , Hashtable.getNextPowerOfTwo( 13 ));
        assertEquals( 16 , Hashtable.getNextPowerOfTwo( 14 ));
        assertEquals( 16 , Hashtable.getNextPowerOfTwo( 15 ));
        assertEquals( 16 , Hashtable.getNextPowerOfTwo( 16 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 17 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 18 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 19 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 20 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 21 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 22 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 23 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 24 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 25 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 26 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 27 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 28 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 29 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 30 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 31 ));
        assertEquals( 32 , Hashtable.getNextPowerOfTwo( 32 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 33 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 34 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 35 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 36 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 37 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 38 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 39 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 40 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 41 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 42 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 43 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 44 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 45 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 46 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 47 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 48 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 49 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 50 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 51 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 52 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 53 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 54 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 55 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 56 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 57 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 58 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 59 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 60 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 61 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 62 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 63 ));
        assertEquals( 64 , Hashtable.getNextPowerOfTwo( 64 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 65 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 66 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 67 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 68 ));



        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 69 ));



        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 70 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 71 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 72 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 73 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 74 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 75 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 76 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 77 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 78 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 79 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 80 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 81 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 82 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 83 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 84 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 85 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 86 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 87 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 88 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 89 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 90 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 91 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 92 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 93 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 94 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 95 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 96 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 97 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 98 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 99 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 100 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 101 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 102 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 103 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 104 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 105 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 106 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 107 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 108 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 109 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 110 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 111 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 112 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 113 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 114 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 115 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 116 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 117 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 118 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 119 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 120 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 121 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 122 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 123 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 124 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 125 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 126 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 127 ));
        assertEquals( 128 , Hashtable.getNextPowerOfTwo( 128 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 129 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 130 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 131 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 132 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 133 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 134 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 135 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 136 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 137 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 138 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 139 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 140 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 141 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 142 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 143 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 144 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 145 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 146 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 147 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 148 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 149 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 150 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 151 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 152 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 153 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 154 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 155 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 156 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 157 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 158 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 159 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 160 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 161 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 162 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 163 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 164 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 165 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 166 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 167 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 168 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 169 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 170 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 171 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 172 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 173 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 174 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 175 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 176 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 177 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 178 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 179 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 180 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 181 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 182 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 183 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 184 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 185 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 186 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 187 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 188 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 189 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 190 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 191 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 192 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 193 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 194 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 195 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 196 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 197 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 198 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 199 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 200 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 201 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 202 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 203 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 204 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 205 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 206 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 207 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 208 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 209 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 210 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 211 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 212 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 213 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 214 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 215 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 216 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 217 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 218 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 219 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 220 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 221 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 222 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 223 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 224 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 225 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 226 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 227 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 228 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 229 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 230 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 231 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 232 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 233 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 234 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 235 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 236 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 237 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 238 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 239 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 240 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 241 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 242 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 243 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 244 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 245 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 246 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 247 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 248 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 249 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 250 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 251 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 252 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 253 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 254 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 255 ));
        assertEquals( 256 , Hashtable.getNextPowerOfTwo( 256 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 257 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 258 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 259 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 260 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 261 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 262 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 263 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 264 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 265 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 266 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 267 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 268 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 269 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 270 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 271 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 272 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 273 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 274 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 275 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 276 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 277 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 278 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 279 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 280 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 281 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 282 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 283 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 284 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 285 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 286 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 287 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 288 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 289 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 290 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 291 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 292 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 293 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 294 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 295 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 296 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 297 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 298 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 299 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 300 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 301 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 302 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 303 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 304 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 305 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 306 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 307 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 308 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 309 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 310 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 311 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 312 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 313 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 314 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 315 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 316 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 317 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 318 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 319 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 320 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 321 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 322 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 323 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 324 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 325 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 326 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 327 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 328 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 329 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 330 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 331 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 332 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 333 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 334 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 335 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 336 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 337 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 338 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 339 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 340 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 341 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 342 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 343 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 344 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 345 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 346 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 347 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 348 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 349 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 350 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 351 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 352 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 353 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 354 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 355 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 356 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 357 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 358 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 359 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 360 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 361 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 362 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 363 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 364 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 365 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 366 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 367 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 368 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 369 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 370 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 371 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 372 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 373 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 374 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 375 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 376 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 377 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 378 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 379 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 380 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 381 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 382 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 383 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 384 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 385 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 386 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 387 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 388 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 389 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 390 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 391 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 392 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 393 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 394 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 395 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 396 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 397 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 398 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 399 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 400 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 401 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 402 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 403 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 404 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 405 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 406 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 407 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 408 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 409 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 410 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 411 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 412 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 413 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 414 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 415 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 416 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 417 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 418 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 419 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 420 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 421 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 422 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 423 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 424 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 425 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 426 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 427 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 428 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 429 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 430 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 431 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 432 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 433 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 434 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 435 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 436 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 437 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 438 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 439 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 440 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 441 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 442 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 443 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 444 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 445 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 446 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 447 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 448 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 449 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 450 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 451 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 452 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 453 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 454 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 455 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 456 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 457 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 458 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 459 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 460 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 461 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 462 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 463 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 464 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 465 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 466 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 467 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 468 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 469 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 470 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 471 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 472 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 473 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 474 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 475 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 476 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 477 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 478 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 479 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 480 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 481 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 482 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 483 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 484 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 485 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 486 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 487 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 488 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 489 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 490 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 491 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 492 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 493 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 494 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 495 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 496 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 497 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 498 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 499 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 500 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 501 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 502 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 503 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 504 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 505 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 506 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 507 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 508 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 509 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 510 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 511 ));
        assertEquals( 512 , Hashtable.getNextPowerOfTwo( 512 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 513 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 514 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 515 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 516 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 517 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 518 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 519 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 520 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 521 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 522 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 523 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 524 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 525 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 526 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 527 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 528 ));
        assertEquals( 1024 , Hashtable.getNextPowerOfTwo( 529 ));
    }

    @Test
    public void fastModulo() {
        assertEquals(36, Hashtable.fastModulo(420, 128));
        assertEquals(85, Hashtable.fastModulo(42069, 256));
        assertEquals(69, Hashtable.fastModulo(35397, 512));
        assertEquals(420, Hashtable.fastModulo(35748, 512) );
        assertEquals(42069, Hashtable.fastModulo(828501, 131072) );
    }

    @Test
    public void insert() {
        Hashtable<Integer, Integer> table = new Hashtable<>(5, new int[] { 0 });
        table.insert(1, 1, (x, y) -> x % y);
        table.insert(1, 2, null);
        table.insert(1, 3, null);
        table.insert(2, 69, null);
        table.insert(3, 420, null);
        table.insert(2, 230, null);
        table.stream().forEach(System.out::println);
        table.remove(2, null);
        System.out.println("---------------------");
        table.stream().forEach(System.out::println);
        System.out.println("---------------------");
        table.insert(2, 420420, null);
        table.stream().forEach(System.out::println);
    }

    @Test
    public void simonK() {
        Hashtable<Integer, Integer> table = new Hashtable<>(3, new int[] { 0 });
        table.insert(1, 1, null);
        table.insert(1, 2, null);
        table.insert(1, 3, null);
        System.out.println("---------------------");
        table.stream().forEach(System.out::println);
        table.remove(1, null);
        System.out.println("---------------------");
        table.stream().forEach(System.out::println);
    }

    @Test
    public void simonKeilZulip() {
        Hashtable<Integer, Integer> table = new Hashtable<>(1, new int[] { 0 });
        System.out.println("---------------------");
        System.out.println("---------------------");
        table.insert(1, 1, null);
        table.insert(1, 2, null);
        table.stream().forEach(System.out::println);
        System.out.println("---------------------");
        table.remove(1, null);
        table.stream().forEach(System.out::println);
    }


    @org.junit.jupiter.api.Test
    void fastModuloMikhail() {
        for (int i = Integer.MAX_VALUE; i > Integer.MAX_VALUE - 100000; i--) {
            assertEquals(Math.floorMod(i, (int)Math.pow(2,30)) ,Hashtable.fastModulo(i, (int)Math.pow(2,30)), "failed on "+i);
        }
    }

    @org.junit.jupiter.api.Test
    void ints() {
        testInterval(0, 30000, integer -> integer, integer -> integer);
    }

    @org.junit.jupiter.api.Test
    void stringKeys() {
        testInterval(0, 1000, "a"::repeat, i -> i);
    }

    <K, V> void testInterval(int from, int to, Function<Integer, K> getKey, Function<Integer, V> getValue) {
        Hashtable<K, V> ht = new Hashtable<>(200, new int[]{1,2,3,4}) ;

        // insert all
        for (int i = from; i < to; i++) {
            ht.insert(getKey.apply(i), getValue.apply(i), null);
        }

        // find all
        for (int i = from; i < to; i++) {
            K k = getKey.apply(i);
            assertEquals(ht.find(k, null), Optional.of(getValue.apply(i)), "Couldn't retrieve value for key " + k);
        }

        // remove all
        for (int i = from; i < to; i++) {
            K k = getKey.apply(i);
            assertTrue(!ht.find(k, null).isPresent() || ht.remove(k, null), "Wrong remove return value for " + k);
        }

        // remove all again
        for (int i = from; i < to; i++) {
            K k = getKey.apply(i);
            assertFalse(ht.remove(k, null), "Wrong remove return value for " + k);
        }

        // find all again
        for (int i = from; i > to; i++) {
            K k = getKey.apply(i);
            assertTrue(ht.find(k, null).isEmpty(), "Removed but found " + k);
        }
    }

    @org.junit.jupiter.api.Test
    void simple() {
        Hashtable<Integer, Integer> table = new Hashtable<>(5, new int[] { 1, 2, 3, 4 });
        table.insert(1, 1, null);
        table.insert(1, 2, null);
        table.insert(1, 3, null);
        table.insert(2, 69, null);
        table.insert(3, 420, null);
        table.insert(2, 230, null);

        assertTrue(table.remove(1, null));
        assertFalse(table.remove(1, null));
        assertTrue(table.remove(2, null));
        assertTrue(table.remove(3, null));
    }

}
