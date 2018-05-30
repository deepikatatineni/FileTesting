package com.example.filetesting

import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adapter: FileAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = FileAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter

        start.setOnClickListener {
            startScan()
        }
    }

    private fun startScan() {
        RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe {
                    if (it) {
                        progressBar.visibility = View.VISIBLE
                        Observable.create<List<FileModel>> {
                            val files = M.listFilesAndFilesSubDirectories(Environment.getExternalStorageDirectory(), ArrayList())
                            val byDescending = files.sortedByDescending {
                                it.length
                            }
                            it.onNext(byDescending)
                        }.observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .doOnComplete {
                                    progressBar.visibility = View.GONE
                                }
                                .subscribe({
                                    progressBar.visibility = View.GONE
                                    adapter?.updateList(it)
                                }, {})

                    }
                }
    }


}
