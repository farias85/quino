/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Created by Felipe Rodriguez Arias <ucifarias@gmail.com> on 28/06/2013.
 */
package quino.util;

import java.util.Random;

/**
 * Representa un número aleatorioø
 */
public class Aleatorio extends Random {

    /**
     * Devuelve un valor entero entre el valor inf y el sup
     * @param inf Limite inferior
     * @param sup Limite superior
     * @return Un número entero entre inf y sup
     */
    public int nextInt(int inf, int sup) {
        int i = nextInt();
        i = inf + (Math.abs(i) % (sup - inf + 1));
        return (i);
    }
}
