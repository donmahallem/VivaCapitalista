/*
 * Copyright 2015 https://github.com/donmahallem/VivaCapitalista
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.xants.capitalista.model.otto;

import de.xants.capitalista.model.Multiplier;

public class UpgradeMultiplierEvent {
    public final Multiplier MULTIPLIER;

    private UpgradeMultiplierEvent(Multiplier multiplier) {
        this.MULTIPLIER = multiplier;
    }

    public static UpgradeMultiplierEvent create(Multiplier multiplier) {
        return new UpgradeMultiplierEvent(multiplier);
    }
}
