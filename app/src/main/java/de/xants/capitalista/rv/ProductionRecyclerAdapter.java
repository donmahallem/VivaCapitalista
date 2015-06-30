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

package de.xants.capitalista.rv;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.xants.capitalista.CM;
import de.xants.capitalista.model.Production;
import de.xants.capitalista.model.ProductionType;

public class ProductionRecyclerAdapter extends RecyclerView.Adapter<ProductionViewHolder> {
    private List<Production> mProductionList = new ArrayList<Production>();

    public ProductionRecyclerAdapter() {
        this.mProductionList.add(new Production(ProductionType.TYPE1));
        this.mProductionList.add(new Production(ProductionType.TYPE2));
        this.mProductionList.add(new Production(ProductionType.TYPE3));
        this.mProductionList.add(new Production(ProductionType.TYPE4));
        this.mProductionList.add(new Production(ProductionType.TYPE5));
        this.mProductionList.add(new Production(ProductionType.TYPE6));
        this.mProductionList.add(new Production(ProductionType.TYPE7));
        this.mProductionList.add(new Production(ProductionType.TYPE8));
        this.mProductionList.add(new Production(ProductionType.TYPE9));
        this.mProductionList.add(new Production(ProductionType.TYPE1));
        this.mProductionList.add(new Production(ProductionType.TYPE2));
        this.mProductionList.add(new Production(ProductionType.TYPE3));
        this.mProductionList.add(new Production(ProductionType.TYPE4));
        this.mProductionList.add(new Production(ProductionType.TYPE5));
        this.mProductionList.add(new Production(ProductionType.TYPE6));
        this.mProductionList.add(new Production(ProductionType.TYPE7));
        this.mProductionList.add(new Production(ProductionType.TYPE8));
        this.mProductionList.add(new Production(ProductionType.TYPE9));
    }

    @Override
    public ProductionViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        return new ProductionViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(ProductionViewHolder viewHolder, int position) {
        viewHolder.setData(this.mProductionList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mProductionList.size();
    }

    @Override
    public void onViewAttachedToWindow(ProductionViewHolder holder) {
        CM.getBus().register(holder);
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(ProductionViewHolder holder) {
        CM.getBus().unregister(holder);
        super.onViewDetachedFromWindow(holder);
    }
}
