package ru.kkuzmichev.simpleappforespresso.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.kkuzmichev.simpleappforespresso.R;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private List<GalleryItem> itemList;

    public GalleryAdapter(List<GalleryItem> itemList) { // Лучше указать тип списка явно
        this.itemList = itemList;
    }

    // ❌ УДАЛИ МЕТОД getItemViewType ПОЛНОСТЬЮ. Он здесь не нужен и вреден.

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Используем конкретный ID ресурса напрямую, а не через viewType
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GalleryItem item = itemList.get(position);

        // ✅ Безопасная обработка null
        String title = (item.getTitle() != null) ? item.getTitle() : "";
        String description = (item.getDescription() != null) ? item.getDescription() : "";

        holder.itemTitle.setText(title);
        holder.itemDescription.setText(description);
        holder.itemNumber.setText(String.valueOf(item.getNumber()));
    }

    @Override
    public int getItemCount() {
        return (itemList != null) ? itemList.size() : 0; // Защита на случай, если список null
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTitle;
        private TextView itemDescription;
        private TextView itemNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemDescription = itemView.findViewById(R.id.item_description);
            itemNumber = itemView.findViewById(R.id.item_number);
        }
    }
}
